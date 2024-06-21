import {AccountService} from "../service/account.service";
import {ICreateAccountRequest} from "../types";
import {NextFunction, Response} from "express";

export class AccountController{
    accountService: AccountService;
    constructor(accountService: AccountService) {
        this.accountService = accountService;
    }

    createAccount = async (req: ICreateAccountRequest, res: Response, next: NextFunction) => {
        try {
            res.status(200).json({
                data: await this.accountService.createAccount(req, req.path),
                message: "Account created successful"
            });
        }catch (e){
            next(e)
        }
    }

    getAccountById = async (req: ICreateAccountRequest, res: Response, next: NextFunction) => {
        try {
            res.status(200).json({
                data: await this.accountService.getAccountById(req, req.path),
                message: "Account created successful"
            });
        }catch (e){
            next(e)
        }
    }
}