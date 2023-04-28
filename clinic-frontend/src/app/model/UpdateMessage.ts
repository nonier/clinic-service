export class UpdateMessage {
  text: string;
  userFromId: number;
  dialogId: number;


  constructor(text: string, userFromId: number, dialogId: number) {
    this.text = text;
    this.userFromId = userFromId;
    this.dialogId = dialogId;
  }
}
