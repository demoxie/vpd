import {APIException} from "../exceptions";
import {IMakePaymentRequest} from "../types";
import webClient from "../config/api-client";

export class TransactionService{

  makePayment = async(req: IMakePaymentRequest)=>{
      return await webClient.post("/transaction-services/make-payment", {
          amount: req.body.amount,
          accountNumber: req.body.accountNumber
      })
         .then(response => response.data)
         .catch(err => {
             throw new APIException(err?.response?.data?.message, err?.response?.data?.path);
          });
  }
}
