import { Pipe, PipeTransform } from '@angular/core';
import {Consultation} from "../model/Consultation";

@Pipe({
  name: 'freeConsultation'
})
export class FreeConsultationPipe implements PipeTransform {

  transform(consultations: Consultation[], ...args: unknown[]): Consultation[] {
    return consultations
      .filter((cons) => cons.client === null);
  }

}
