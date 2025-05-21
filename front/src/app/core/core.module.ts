// src/app/core/core.module.ts
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { HTTP_INTERCEPTORS }            from '@angular/common/http';

// Services & Guards
import { AuthService }                  from './services/auth.service';
import { AuthGuard }                    from './guards/auth.guard';

// Interceptors
import { AuthInterceptor }              from './interceptors/auth.interceptor';

@NgModule({
  providers: [
    // Singleton services
    AuthService,
    AuthGuard,
    // Interceptor JWT
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parent: CoreModule) {
    if (parent) {
      throw new Error('CoreModule is already loaded. Import it in AppModule only.');
    }
  }
}
