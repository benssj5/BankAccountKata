import { Account } from "app/models/account";

export class Operation {

    numero: number;
    dateOperation: Date;
    amount: number;
    type_op: string;
    account: Account;

    constructor( numero: number=0,
    dateOperation: Date = new Date(),
    amount: number=1000,
    type_op: string='D',//'D' or 'W'
    account: Account=new Account()){ 
        this.numero = numero;
        this.dateOperation = dateOperation;
        this.type_op = type_op;
        this.account = account;
     } 
} 