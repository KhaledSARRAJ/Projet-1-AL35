import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceListeUtiComponent } from './annonce-liste-uti.component';

describe('AnnonceListeUtiComponent', () => {
  let component: AnnonceListeUtiComponent;
  let fixture: ComponentFixture<AnnonceListeUtiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnonceListeUtiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceListeUtiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
