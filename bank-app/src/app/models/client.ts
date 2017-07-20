import { Account } from "app/models/account";

export class Client {

    code: number;
    name: string;
    email: string;
    password: string;
    account: Account ;

    constructor(code : number = 0, 
                name:string = "",
                email: string = "",
                password: string = "",
                account:Account  = new Account()){ 
        this.code = code;
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account;
     } 
} 