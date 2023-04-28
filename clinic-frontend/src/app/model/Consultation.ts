import {User} from "./User";

export class Consultation {

  id: number;
  date: Date;
  client: User;
  dialogId: number;

  constructor(id: number, date: Date, client: User, dialogId: number) {
    this.id = id;
    this.date = date;
    this.client = client;
    this.dialogId = dialogId;
  }
}
