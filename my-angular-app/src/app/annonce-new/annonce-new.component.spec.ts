import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceNewComponent } from './annonce-new.component';

describe('AnnonceNewComponent', () => {
  let component: AnnonceNewComponent;
  let fixture: ComponentFixture<AnnonceNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnonceNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
