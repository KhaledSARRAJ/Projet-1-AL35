import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceListeComponent } from './annonce-liste.component';

describe('AnnonceListeComponent', () => {
  let component: AnnonceListeComponent;
  let fixture: ComponentFixture<AnnonceListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnonceListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
