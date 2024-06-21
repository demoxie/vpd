import {Express, Router} from "express";
import {UserController} from "../controller/user.controller";

export class Routes{
    router: Router;
    userController: UserController;
    constructor(app: Router, userController: UserController) {
        this.router = app;
        this.userController = userController
    }
    public getRoutes = ()=>{
        const {signup, login, validateToken, updateCustomer, deleteCustomer} = this.userController;


        this.router.post("/signup",signup);
        this.router.post("/login",login);
        this.router.post("/validateToken",validateToken);
        this.router.put("/profiles/:userId",updateCustomer);
        this.router.delete("/profiles/:userId",deleteCustomer);
        return this.router;
    }
}
