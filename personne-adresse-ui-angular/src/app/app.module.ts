import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PersonnesComponent } from './application/component/personnes/personnes.component';
import { HeaderComponent } from './application/component/header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SimpleSpanProcessor } from '@opentelemetry/tracing';
import { WebTracerProvider } from '@opentelemetry/web';
import { XMLHttpRequestPlugin } from '@opentelemetry/plugin-xml-http-request';
import { environment } from 'src/environments/environment';
import { CollectorExporter } from '@opentelemetry/exporter-collector'

export function initializeTracer() {
  return () => {
    const collectorOptions = {
      serviceName: 'personne-adresse-angular',
      //url: 'http://localhost:55678' // url is optional and can be omitted - default is http://localhost:55678/v1/trace
    };

    const provider = new WebTracerProvider({
      plugins: [
        new XMLHttpRequestPlugin({
          propagateTraceHeaderCorsUrls: [environment.urlPersonneApi]
        })
      ]
    });
    const exporter = new CollectorExporter(collectorOptions);
    provider.addSpanProcessor(new SimpleSpanProcessor(exporter));

    provider.register();
  }
}

@NgModule({
  declarations: [
    AppComponent,
    PersonnesComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatTableModule,
    MatProgressSpinnerModule,
    NgbModule,
  ],
  //providers: [{ provide: HTTP_INTERCEPTORS, useClass: TracerInterceptor, multi: true }, ],
  providers: [{ provide: APP_INITIALIZER, useFactory: initializeTracer, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
