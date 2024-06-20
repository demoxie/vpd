import express, {Express, Router} from "express";
import "reflect-metadata";
import {Routes} from "./routes";
import morgan from "morgan";
import {requestLogInterceptor, responseLogInterceptor} from "./middlewares";
import {UserService} from "./service/user.service";
import {UserController} from "./controller/user.controller";
import rateLimit from "express-rate-limit";

const app: Express = express();
const router: Router = express.Router();
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutes
  max: 100, 
  message: 'Too many requests from this IP, please try again later'
});

app.use(limiter);
app.use(express.json());
app.use(morgan('dev'));
app.use(requestLogInterceptor);
const userService = new UserService();
const userController = new UserController(userService);
const routes = new Routes(router, userController);
const rs = routes.getRoutes();
app.use("/api/v1",rs);
app.use(requestLogInterceptor);
app.use(responseLogInterceptor);
export default app;
