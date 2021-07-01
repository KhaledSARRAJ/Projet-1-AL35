import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceSupportComponent } from './annonce-support.component';

describe('AnnonceSupportComponent', () => {
  let component: AnnonceSupportComponent;
  let fixture: ComponentFixture<AnnonceSupportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnonceSupportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceSupportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
