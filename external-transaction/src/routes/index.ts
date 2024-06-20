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
        console.log("Hello from");
        const {signup, login, getUserProfile, updateCustomer, deleteCustomer} = this.userController;


        this.router.post("/signup",signup);
        this.router.post("/login",login);
        this.router.get("/profiles/:userId",getUserProfile);
        this.router.put("/profiles/:userId",updateCustomer);
        this.router.delete("/profiles/:userId",deleteCustomer);
        return this.router;
    }
}
