import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMigrations } from 'app/shared/model/migrations.model';

@Component({
  selector: 'jhi-migrations-detail',
  templateUrl: './migrations-detail.component.html'
})
export class MigrationsDetailComponent implements OnInit {
  migrations: IMigrations;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ migrations }) => {
      this.migrations = migrations;
    });
  }

  previousState() {
    window.history.back();
  }
}
