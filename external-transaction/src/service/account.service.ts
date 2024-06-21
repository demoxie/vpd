import webClient from "../config/api-client";
import {ICreateAccountRequest} from "../types";
import {APIException} from "../exceptions";

export class AccountService{
    createAccount = async(req: ICreateAccountRequest)=> {
        return await webClient.post("/account-services/create", {
            name: req.body.name,
            description: req.body.description
        })
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw new APIException(err?.response?.data?.message, err?.response?.data?.path);
            });
    }

        getAccountById = async (accountId: number) => {
            return await webClient.get("/account-services/getAccountById?accountId=" + accountId)
                .then(response => {
                    return response.data;
                })
                .catch(err => {
                    throw new APIException(err?.response?.data?.message, err?.response?.data?.path);
                });
        }

        getAccountByAccountNumber = async (accountNumber: string)=>{
        return await webClient.get("/account-services/getAccountByAccountNumber?accountNumber=" + accountNumber)
            .then(response => {
                return response.data;
            })
           .catch(err => {
                throw new APIException(err?.response?.data?.message, err?.response?.data?.path);
            });
        }
}