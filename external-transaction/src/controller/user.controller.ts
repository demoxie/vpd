import * as express from "express";
import {NextFunction, Request, Response} from "express";
import {UserService} from "../service/user.service";
import {ISignupRequest, IUpdateProfileRequest, IUserIdRequest, LoginRequest} from "../types";




export class UserController{
    userService: UserService;
    constructor(userService: UserService) {
        this.userService = userService;
    }

    public signup = async (req: ISignupRequest, res: Response, next: NextFunction) => {
        try {
            console.log("Heo guys");
            const result = await this.userService.createProfile(req, req.path)
            res.status(200).send({
                data: result,
                message: "Sign up successful"
            });
        }catch (e){
            next(e)
        }
    };

    public login = async(req: LoginRequest, res: Response, next: NextFunction) => {
        try {
            res.status(200).json({
                data: await this.userService.login(req, req.path),
                message: "Profiles fetched successful"
            })
        }catch (e){
            next(e)
        }
    };

    public getUserProfile = async (req: IUserIdRequest, res: Response, next: NextFunction) => {
        try {
            res.status(200).json({
                data: await this.userService.getUserProfile(parseInt(req.params.userId), req.path),
                message: "Profile fetched successful"
            })
        }catch (e){
            next(e)
        }
    };

    public updateCustomer = async(req: IUpdateProfileRequest, res: Response, next: NextFunction) => {
        try {
            res.status(200).json({
                data: await this.userService.updateUserProfile(req, req.path),
                message: "Profile updated successful"
            })
        }catch (e){
            next(e)
        }
    };

    public deleteCustomer = async(req: IUserIdRequest, res: Response, next: NextFunction) => {
        try {
            res.status(200).json({
                data: await this.userService.deleteUserProfile(req, req.path),
                message: "Profile deleted successful"
            })
        }catch (e){
            next(e)
        }
    };
}
