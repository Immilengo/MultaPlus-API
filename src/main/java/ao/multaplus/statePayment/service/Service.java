package ao.multaplus.statePayment.service;

import ao.multaplus.statePayment.dtos.StatusPaymentDTO;
import ao.multaplus.statePayment.dtos.StatusPaymentSaveDTO;
import ao.multaplus.statePayment.entity.Mensagem;
import ao.multaplus.statePayment.entity.StatusPayment;
import ao.multaplus.statePayment.repository.StatusPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service
public class Service implements StatusPaymentService{

    @Autowired
    private Mensagem sms;

    @Autowired
    private StatusPaymentRepository statusPayment;


    @Override
    public ResponseEntity<?> listar(){
        List<StatusPayment> all = statusPayment.findAll();
        return new ResponseEntity<>( all, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> deletar(long id){
        StatusPayment tate = statusPayment.findById(id);
        statusPayment.delete(tate);
        sms.setmensagem("Estado do Pagamento Deletado Com Sucesso");
        return new ResponseEntity<>(sms, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> editar(long id,StatusPaymentSaveDTO state){
        StatusPayment stat= statusPayment.findById(id);
        if (stat == null){
            sms.setmensagem("Status-Payment not Found");
            return new ResponseEntity<>(sms, HttpStatus.NOT_FOUND);
        }
        stat.setId(id);
        stat.setState(state.state());
        stat.setDescription(state.description());
        if(stat.getState().equals("")){
            sms.setmensagem("O estado do pagamento não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setmensagem("Actualizado com Sucesso");
            statusPayment.save(stat);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> cadastrar(StatusPaymentSaveDTO state){
        StatusPayment status=new StatusPayment();
        status.setState(state.state());
        status.setDescription(state.description());
        if(status.getState().equals("")){
            sms.setmensagem("O estado do Pagamento não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setmensagem("Salvo com Sucesso");
            statusPayment.save(status);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> buscar(long id){
        StatusPayment status = statusPayment.findById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
