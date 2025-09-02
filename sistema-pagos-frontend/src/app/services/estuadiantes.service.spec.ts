import { TestBed } from '@angular/core/testing';

import { EstuadiantesService } from './estuadiantes.service';

describe('EstuadiantesService', () => {
  let service: EstuadiantesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstuadiantesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
