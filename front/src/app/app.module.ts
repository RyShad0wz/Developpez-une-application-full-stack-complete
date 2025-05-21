import { NgModule }                from '@angular/core';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule }        from '@angular/common/http';
import { ReactiveFormsModule }     from '@angular/forms';

import { AppRoutingModule }        from './app-routing.module';
import { CoreModule }              from './core/core.module';
import { SharedModule }            from './shared/shared.module';

import { AppComponent }            from './app.component';
import { HomeComponent }           from './pages/home/home.component';
import { LoginComponent }          from './pages/auth-login/login.component';
import { RegisterComponent }       from './pages/auth-register/register.component';
import { TopicsComponent }         from './pages/topics-list/topics.component';
import { ArticlesComponent }       from './pages/articles-list/articles.component';
import { ArticleCreateComponent }  from './pages/article-create/article-create.component';
import { ArticleDetailComponent }  from './pages/article-detail/article-detail.component';
import { ProfileComponent }        from './pages/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    TopicsComponent,
    ArticlesComponent,
    ArticleCreateComponent,
    ArticleDetailComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CoreModule,
    SharedModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
