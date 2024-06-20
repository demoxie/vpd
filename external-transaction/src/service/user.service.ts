import {ISignupRequest, IUpdateProfileRequest, IUserIdRequest, LoginRequest} from "../types";
import {AppDataSource} from "../config/db/data-source";
import {User} from "../entity/user.entity";
import {AlreadyExistException, APIException, DatabaseError, NotFoundException} from "../exceptions";
import webClient from "../config/api-client";

export class UserService{

    signup = async (req: ISignupRequest, path: string) => {
        return await webClient.post("/auth/register",{
            email:req.body.email,
            password:req.body.password,
            name:req.body.name
        })
       .then(response =>{
            return response.data;
        })
          .catch(error=>{
            throw new APIException(error.message,path);
          })
    };

    login = async (loginRequest: LoginRequest, path: string) => {
        return await webClient.post("/auth/login", {
            email: loginRequest.body.email,
            password:  loginRequest.body.password
        })
          .then(response =>{
            return response.data;
          })
          .catch(err =>{
            throw new APIException(err.message, path);
          })
    };

    getUserProfile = async (userId: number, path: string) => {
        const existingUser = await AppDataSource.getRepository(User).findOneBy({
            id: userId
        })
            .catch(err=>{
                throw new DatabaseError(err, path);
            });
        if(!existingUser){
            throw new NotFoundException("User not found",path);
        }
        return existingUser;
    };

    updateUserProfile = async (req: IUpdateProfileRequest, path: string) => {
        const existingUser = await AppDataSource.getRepository(User).findOneBy({
            id: parseInt(req.params.userId)
        })
            .catch(err=>{
                throw new DatabaseError(err, path);
            });
        if(!existingUser){
            throw new NotFoundException("User not found",path);
        }
        UserService.mapUpdatableFields(existingUser, req);
        return await AppDataSource.manager.save(existingUser)
            .catch(err=>{
                throw new DatabaseError(err, path);
            });
    };

    private static mapUpdatableFields(existingUser: User, req: IUpdateProfileRequest) {
        existingUser.firstName = req.body.firstName ? req.body.firstName : existingUser.firstName;
        existingUser.lastName = req.body.lastName ? req.body.lastName : existingUser.lastName;
        existingUser.phoneNumber = req.body.phoneNumber ? req.body.phoneNumber : existingUser.phoneNumber;
        existingUser.address = req.body.address ? req.body.address : existingUser.address;
    }

    deleteUserProfile = async (req: IUserIdRequest, path: string) => {
        const existingUser = await AppDataSource.getRepository(User).findOneBy({
            id: parseInt(req.params.userId)
        });
        if(!existingUser){
            throw new NotFoundException("User not found",path);
        }
        await AppDataSource.getRepository(User).delete({
            id: parseInt(req.params.userId)
        });
    };
}
