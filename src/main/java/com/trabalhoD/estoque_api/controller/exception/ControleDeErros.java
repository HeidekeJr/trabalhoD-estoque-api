package com.trabalhoD.estoque_api.controller.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControleDeErros {

    // Erro a: Registro não encontrado (HTTP 404)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleNotFound(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Erro b: Algum erro ao acessar o banco de dados (HTTP 500)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseAccessError(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro fatal: Falha ao acessar o banco de dados. Verifique a conexão do servidor.");
    }

    // Erro c: Falta de parâmetro obrigatório para consulta ou persistência (HTTP 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de validação: Verifique os parâmetros obrigatórios fornecidos na requisição.");
    }

    // Erro d: Erro de integridade, ao deletar registro usado em outro cadastro (HTTP 409)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleIntegrityError(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Erro de integridade: Não é possível deletar um registro que está vinculado a outro cadastro no sistema.");
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<String> handleLoginInvalido(LoginInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioJaExistenteException.class)
    public ResponseEntity<String> handleUsuarioJaExiste(UsuarioJaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    // Erro genérico (Para qualquer outra falha não mapeada)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno inesperado no servidor.");
    }
}