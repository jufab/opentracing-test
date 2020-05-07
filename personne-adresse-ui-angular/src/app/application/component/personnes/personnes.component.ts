import { Component, OnInit, ViewChild } from '@angular/core';
import { PersonneService } from 'src/app/infrastructure/service/personne/personne.service';
import { PersonnesDataSource } from './personnes.datasource';

@Component({
  selector: 'app-personnes',
  templateUrl: './personnes.component.html',
  styleUrls: ['./personnes.component.css']
})
export class PersonnesComponent implements OnInit {
  displayedColumns: string[] = ['idPersonne', 'nom', 'prenom'];
  dataSource : PersonnesDataSource;

  constructor(private personneService: PersonneService) { }

  ngOnInit(): void {
    this.dataSource = new PersonnesDataSource(this.personneService);
    this.dataSource.loadPersonnes();
  }
}
