package clinica.strategies.impl;

import clinica.BancoDeDados;
import clinica.TabelaEspecialidades;
import clinica.strategies.PagamentoStrategy;

public class PagamentoSemCobertura implements PagamentoStrategy {

    private TabelaEspecialidades tabela;
    private String especialidade;

    public PagamentoSemCobertura(String especialidade) {
        BancoDeDados bd = BancoDeDados.getInstance();
        this.tabela = (TabelaEspecialidades) bd.selectIndex(TabelaEspecialidades.class, 0);
        this.especialidade = especialidade;
    }

    @Override
    public double pagar() {
        return tabela.getEspecialidades().get(especialidade);
    }


}
