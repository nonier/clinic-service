import {User} from "./User";

export class Consultation {

  id: number;
  date: Date;
  client: User;
  doctor: User;
  dialogId: number;

  constructor(id: number, date: Date, client: User, doctor: User, dialogId: number) {
    this.id = id;
    this.date = date;
    this.client = client;
    this.doctor = doctor;
    this.dialogId = dialogId;
  }
}
