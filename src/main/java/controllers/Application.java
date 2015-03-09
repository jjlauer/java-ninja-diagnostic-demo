package controllers;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ninja.Context;
import ninja.FilterWith;
import ninja.Ninja;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.exceptions.BadRequestException;
import ninja.i18n.Lang;
import ninja.params.PathParam;
import ninja.validation.Required;

/**
 *
 * @author joelauer
 */
@Singleton
public class Application {
    
    private final Ninja ninja;
    private final Lang lang;
    
    @Inject
    public Application(Ninja ninja, Lang lang) {
        this.ninja = ninja;
        this.lang = lang;
    }
    
    public Result index() {
        return Results.ok();
    }
    
    public Result template_error() {
        return Results.ok();
    }
    
    public Result bad_template() {
        return Results.ok();
    }
    
    public Result missing_template() {
        return Results.ok();
    }
    
    public Result app_error() {
        //Test.Internal a = new Test.Internal();
        //return Results.ok();
        throw new RuntimeException("Error during controller action");
    }
    
    public Result bad_request(@Required @PathParam("id") Integer id) {
        throw new BadRequestException(new IllegalArgumentException("id is required"));
    }
    
    public Result dependency_error() {
        // error triggered within a dependency (stacktrace needs to find where
        // in this app it actually occurred from)
        String nullValue = null;
        String resultValue = Optional.of(nullValue).or("value instead");
        return Results.ok().render(resultValue);
    }
    
    @FilterWith(SecureFilter.class) 
    public Result forbidden() {
        return Results.TODO();
    }
    
    public Result not_authorized(Context context) {
        return ninja.getUnauthorizedResult(context);
    }
    
}
