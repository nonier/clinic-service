import {Injectable} from '@angular/core';
import {BehaviorSubject, map, Observable} from "rxjs";
import {Message} from "../model/Message";
import {Doctor} from "../model/Doctor";
import {Specialization} from "../model/Specialization";
import {Consultation} from "../model/Consultation";


@Injectable({
  providedIn: 'root'
})
export class DataService {

  messages: BehaviorSubject<Map<number, Message>> = new BehaviorSubject(new Map());
  doctors: BehaviorSubject<Doctor[]> = new BehaviorSubject([]);
  specializations: BehaviorSubject<Specialization[]> = new BehaviorSubject([]);
  userConsultations: BehaviorSubject<Map<number, Consultation>> = new BehaviorSubject(new Map());

  updateMessages(messages: Message[]) {
    let oldMessages = this.messages.value;
    messages.map(message => oldMessages.set(message.id, message));
    this.messages.next(oldMessages);
  }

  getMessages(dialogId: number): Observable<Message[]> {
    return this.messages.pipe(
      map(message => {
        let messages: Message[] = []
        message.forEach((v, k) => { if (v.dialogId == dialogId) messages.push(v) })
        messages.sort((a, b) => a.creationDate > b.creationDate ? 1 : 0);
        return messages;
      })
    )
  }

  updateDoctors(doctors: Doctor[]) {
    this.doctors.next(doctors);
  }

  getDoctors(): Observable<Doctor[]> {
    return this.doctors;
  }

  updateSpecializations(specializations: Specialization[]) {
    this.specializations.next(specializations);
  }

  getSpecializations(): Observable<Specialization[]> {
    return this.specializations;
  }

  updateConsultations(consultations: Consultation[]) {
    let oldUserConsultations = this.userConsultations.value;
    consultations.map(consultation => oldUserConsultations.set(consultation.id, consultation));
    this.userConsultations.next(oldUserConsultations);
  }

  getConsultations(): Observable<Consultation[]> {
    return this.userConsultations
      .pipe(
        map((consultations)=> {
          return Array.from(consultations.values());
        })
      );
  }
}
