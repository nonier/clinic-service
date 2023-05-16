import {User} from "./User";

export class UpdateConsultation {

  date: Date;
  clientId: number;
  doctorId: number;

  constructor(date: Date, clientId: number, doctorId: number) {
    this.date = date;
    this.clientId = clientId;
    this.doctorId = doctorId;
  }
}
