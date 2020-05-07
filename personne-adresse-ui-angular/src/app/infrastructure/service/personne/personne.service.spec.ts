import { inject, TestBed } from '@angular/core/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { PersonneService } from './personne.service';

describe('PersonneService', () => {
  let personneService: PersonneService;
  let httpMock: HttpTestingController;

  beforeAll(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [PersonneService]
    });
  });

  beforeEach(inject(
    [HttpClient, HttpTestingController],
    (http: HttpClient, httpCtrl: HttpTestingController) => {
      personneService = new PersonneService(http);
      httpMock = httpCtrl;
    }
  ));

  it('should be created', () => {
    expect(personneService).toBeTruthy();
  });
});
