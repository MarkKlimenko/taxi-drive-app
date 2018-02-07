package systems.vostok.taxi.drive.app.api

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.container.PreMatching
import javax.ws.rs.ext.Provider

@Provider
@PreMatching
class ResponseConfiguration implements ContainerResponseFilter {
    @Override
    void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
        responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*")
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true")
        responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        responseCtx.getHeaders().addAll("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    }
}
