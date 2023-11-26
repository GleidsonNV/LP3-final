package clinica.strategies.impl;

import clinica.Plano;
import clinica.TabelaEspecialidades;
import clinica.strategies.PagamentoStrategy;

public class PagamentoComCobertura implements PagamentoStrategy {

    private TabelaEspecialidades tabela;
    private String especialidade;
    private Plano plano;

    public PagamentoComCobertura(TabelaEspecialidades tabela, String especialidade, Plano plano) {
        this.tabela = tabela;
        this.especialidade = especialidade;
        this.plano = plano;
    }

    @Override
    public double pagar() {
        return this.tabela.getEspecialidades().get(this.especialidade) - (this.plano.getCobertura() / 100.0) * this.tabela.getEspecialidades().get(this.especialidade);
    }
}
