package com.desafio.controle_produtos_pedidos.service;

import com.desafio.controle_produtos_pedidos.domain.exception.DataNotFoundException;
import com.desafio.controle_produtos_pedidos.domain.request.PedidoRequest;
import com.desafio.controle_produtos_pedidos.service.converter.PedidoConverter;
import com.desafio.controle_produtos_pedidos.domain.dto.PedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoPedidoPorPeriodoDTO;
import com.desafio.controle_produtos_pedidos.model.Pedido;
import com.desafio.controle_produtos_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoConverter pedidoConverter;
    private static final int PRECISAO = 2;

    public PedidoService(PedidoRepository pedidoRepository, PedidoConverter pedidoConverter) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoConverter = pedidoConverter;
    }

    public Pedido criarPedido(PedidoRequest pedido) {
        return this.pedidoRepository.save(pedidoConverter.convertRequestToEntity(pedido));
    }

    public PedidoDTO buscarPedidoPorId(Long pedidoId) {
        Pedido pedido = this.pedidoRepository.findById(pedidoId).orElseThrow(() -> new DataNotFoundException("Pedido não encontrado"));
        PedidoDTO pedidoDTO = this.pedidoConverter.convertEntityToDTO(pedido);

        Map<Long, BigDecimal> totalPedido = new HashMap<>();
        pedidoDTO.getProdutos().forEach(produto -> {
            produto.setValorTotalProduto(produto.getPreco());
            calcularValorTotalProdutoPorPedido(totalPedido, pedido.getId(), produto.getValorTotalProduto());
        });

        pedidoDTO.setValorTotalPedido(totalPedido.get(pedidoId) == null ? BigDecimal.ZERO : totalPedido.get(pedidoId));

        return pedidoDTO;
    }

    public List<PedidoDTO> buscarTodosPedidosPorPeriodo(String dataInicialStr, String dataFinalStr) {
        Date dataInicial = parseDate(dataInicialStr);
        Date dataFinal = parseDate(dataFinalStr);

        List<ProdutoPedidoPorPeriodoDTO> pedidos = fetchPedidosPorPeriodo(dataInicial, dataFinal);

        Map<Long, BigDecimal> totalPorPedido = new HashMap<>();
        pedidos.forEach(produtoPedido -> calcularValorTotalProdutoPorPedido(totalPorPedido, produtoPedido.getPedidoId(), produtoPedido.getValorTotalProduto()));
        return buildaPedidosComProdutos(totalPorPedido, pedidos);
    }

    public void excluirPedido(Long id) {
        this.pedidoRepository.deleteById(id);
    }

    private void calcularValorTotalProdutoPorPedido(Map<Long, BigDecimal> totalPorPedido, Long produtoId, BigDecimal totalValorProduto) {
        totalPorPedido.put(produtoId, totalPorPedido.getOrDefault(produtoId, BigDecimal.ZERO).add(totalValorProduto));
    }

    private List<PedidoDTO> buildaPedidosComProdutos(Map<Long, BigDecimal> totalPorPedido, List<ProdutoPedidoPorPeriodoDTO> pedidos) {
        return totalPorPedido.entrySet().stream().map(entry -> {
            PedidoDTO dto = new PedidoDTO();
            Set<ProdutoDTO> produtoDTOList = new HashSet<>();

            dto.setId(entry.getKey());
            dto.setValorTotalPedido(entry.getValue());

            pedidos.stream().filter(f -> Objects.equals(f.getPedidoId(), entry.getKey()))
                    .forEach(pedido -> {
                        ProdutoDTO produto = ProdutoDTO.builder()
                                .id(pedido.getProdutoId())
                                .descricao(pedido.getDescricaoProduto())
                                .valorTotalProduto(pedido.getValorTotalProduto())
                                .build();
                        produtoDTOList.add(produto);
                        dto.setProdutos(produtoDTOList);
                        dto.setData(pedido.getDataPedido());
                    });

            return dto;
        }).collect(Collectors.toList());
    }

    private List<ProdutoPedidoPorPeriodoDTO> fetchPedidosPorPeriodo(Date dataInicial, Date dataFinal) {
        List<Object[]> results = pedidoRepository.findPedidosPorPeriodo(dataInicial, dataFinal);
        return results.stream().map(result -> ProdutoPedidoPorPeriodoDTO.builder()
                .pedidoId((Long) result[0])
                .dataPedido((Date) result[1])
                .produtoId((Long) result[2])
                .descricaoProduto((String) result[3])
                .quantidade(formatDecimal((BigDecimal) result[4]))
                .valorVenda(formatDecimal((BigDecimal) result[5]))
                .valorTotalProduto(formatDecimal((BigDecimal) result[6]))
                .build()
        ).collect(Collectors.toList());
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Datas Invalidas", e);
        }
    }
    private BigDecimal formatDecimal(BigDecimal value) {
        return value.setScale(PRECISAO, RoundingMode.HALF_UP);
    }
}
