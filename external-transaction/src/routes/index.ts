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
        const {signup, login, getUserProfile, updateCustomer, deleteCustomer} = this.userController;

        this.router.post("/profiles",signup);
        this.router.get("/profiles",login);
        this.router.get("/profiles/:userId",getUserProfile);
        this.router.put("/profiles/:userId",updateCustomer);
        this.router.delete("/profiles/:userId",deleteCustomer);
        return this.router;
    }
}
