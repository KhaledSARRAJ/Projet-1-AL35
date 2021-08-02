import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationsDetailsComponent } from './publications-details.component';

describe('PublicationsDetailsComponent', () => {
  let component: PublicationsDetailsComponent;
  let fixture: ComponentFixture<PublicationsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicationsDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicationsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
