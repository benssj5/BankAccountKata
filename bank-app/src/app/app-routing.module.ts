import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from "app/components/clientComponent/client.component";
import { OperationComponent } from "app/components/operationComponent/operation.component";
import { HistoryComponent } from "app/components/historyComponent/history.component";
import { ConnectionComponent } from "app/components/connectionComponent/connection.component";
import { DisconnectionComponent } from "app/components/disconnectionComponent/disconnection.component";
import { InscriptionComponent } from "app/components/inscriptionComponent/inscription.component";



 
const routes: Routes = [
  { path: '', redirectTo: '/connection', pathMatch: 'full' },
  { path: 'client',  component: ClientComponent },
  { path: 'operation',     component: OperationComponent },
  { path: 'history',     component: HistoryComponent},
  { path: 'connection',     component: ConnectionComponent },
  { path: 'inscription',     component: InscriptionComponent },
  { path: 'disconnection',     component: DisconnectionComponent }
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}