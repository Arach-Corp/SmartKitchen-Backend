package com.arachcorp.smartkitchen.config;

import com.arachcorp.smartkitchen.entities.*;
import com.arachcorp.smartkitchen.entities.enums.Pericidade;
import com.arachcorp.smartkitchen.entities.pk.UserDispositivoPK;
import com.arachcorp.smartkitchen.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Profile({"test", "jenkins"})
@Configuration
public class TestInstantiationConfig implements CommandLineRunner {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private InformacaoNutricionalRepository informacaoNutricionalRepository;

    @Autowired
    private ItemDispensaRepository itemDispensaRepository;

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserDispositivoRepository userDispositivoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {


        //  Produtos
        final InformacaoNutricional info1 = new InformacaoNutricional(null, "20g", "2g", "0.1g", "0g", "0g", "0g", "20mg", "2.1g", null);
        final Produto p1 = new Produto(null, "Produto 1", "Produto 1 sabor chocolate", "Marca", null, Pericidade.PERECIVEL, info1);
        info1.setProduto(p1);

        final InformacaoNutricional info2 = new InformacaoNutricional(null, "20g", "2g", "0.1g", "0g", "0g", "0g", "20mg", "2.1g", null);
        final Produto p2 = new Produto(null, "Produto 1", "Produto 1 sabor morango", "Marca", null, Pericidade.PERECIVEL, info2);
        info2.setProduto(p2);

        produtoRepository.saveAll(Arrays.asList(p1, p2));


        //  Lotes
        final LocalDateTime dataFabricacao = LocalDateTime.now();
        final Lote lote1 = new Lote(null, dataFabricacao, dataFabricacao.plusMonths(3), p1);
        final Lote lote2 = new Lote(null, dataFabricacao.plusMonths(3), dataFabricacao.plusMonths(6), p1);
        final Lote lote3 = new Lote(null, dataFabricacao, dataFabricacao.plusMonths(3), p2);
        final Lote lote4 = new Lote(null, dataFabricacao.plusMonths(3), dataFabricacao.plusMonths(6), p2);
        loteRepository.saveAll(Arrays.asList(lote1, lote2, lote3, lote4));


        // Roles
        final Role role1;
        final Role role2;

        if (!roleRepository.existsByDescricao("CLIENT")) {
            role1 = new Role(null, "CLIENT");
            roleRepository.save(role1);
        } else {
            role1 = roleRepository.findByDescricaoIsLike("CLIENT").get();
        }

        if (!roleRepository.existsByDescricao("ADMIN")) {
            role2 = new Role(null, "ADMIN");
            roleRepository.save(role2);
        } else {
            role2 = roleRepository.findByDescricaoIsLike("ADMIN").get();
        }

        // Users
        final User user1 = new User(null, "ADMIN", "admin@gmail.com", encoder.encode("teste123"), null);
        user1.getRoles().add(role1);

        final User user2 = new User(null, "CLIENT", "client@gmail.com", encoder.encode("teste123"), null);
        user2.getRoles().add(role2);

        userRepository.saveAll(Arrays.asList(user1, user2));


        // Notificacao
        final Notificacao n1 = new Notificacao(null, user1, "Painel adminitrativo", "acesse o painel administrativo");
        final Notificacao n2 = new Notificacao(null, user1, "Painel adminitrativo 2", "acesse o painel administrativo 2");
        final Notificacao n3 = new Notificacao(null, user2, "Novo usuário cadastrados", "Seja bem vindo");
        user1.getNotificacoes().add(n1);
        user2.getNotificacoes().add(n2);
        notificacaoRepository.saveAll(Arrays.asList(n1, n2, n3));


        // Dispositivo
        final Dispositivo d1 = new Dispositivo(null, "Casa 1", "st42sad4sa5dsa45d");
        final Dispositivo d2 = new Dispositivo(null, "Casa 2", "st42sa98sadsa4d23");
        dispositivoRepository.saveAll(Arrays.asList(d1, d2));


        // UserDispositivo
        final UserDispositivo ud1 = new UserDispositivo(new UserDispositivoPK(user2, d1), true);
        final UserDispositivo ud2 = new UserDispositivo(new UserDispositivoPK(user2, d2), false);
        user2.getDispositivos().addAll(Arrays.asList(ud1, ud2));
        userDispositivoRepository.saveAll(Arrays.asList(ud1, ud2));


        // Item Dispensa
        final ItemDispensa item1 = new ItemDispensa(null, 1, true, lote1, d1);
        final ItemDispensa item2 = new ItemDispensa(null, 10, true, lote3, d2);
        d1.getItemsDispensa().add(item1);
        d2.getItemsDispensa().add(item2);
        itemDispensaRepository.saveAll(Arrays.asList(item1, item2));
    }
}
