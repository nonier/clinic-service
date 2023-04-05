import {User} from "./User";

export class Consultation {

  id: number;
  date: Date;
  client: User;

  constructor(id: number, date: Date, client: User) {
    this.id = id;
    this.date = date;
    this.client = client;
  }
}
