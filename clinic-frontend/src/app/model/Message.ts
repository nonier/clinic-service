import {User} from "./User";

export class Message {

  id: number;
  text: string;
  dialogId: number;
  creationDate: Date;
  userFrom: User;

  constructor(id: number, text: string, dialogId: number, creationDate: Date, userFrom: User) {
    this.id = id;
    this.text = text;
    this.creationDate = creationDate;
    this.userFrom = userFrom;
  }
}
