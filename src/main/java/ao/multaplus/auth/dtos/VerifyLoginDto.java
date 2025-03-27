package ao.multaplus.auth.dtos;

public record VerifyLoginDto(String email,
                             String password,
                             Integer code) {
}
