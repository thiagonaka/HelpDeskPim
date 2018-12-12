package br.com.helpdeskpim.helpdeskpim.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.helpdeskpim.helpdeskpim.R;
import br.com.helpdeskpim.helpdeskpim.bean.OrdemServicoBean;

public class OrdemServicoAdapter extends RecyclerView.Adapter<OrdemServicoAdapter.OrdemServicoViewHolder> {
    private List<OrdemServicoBean> ordemServicoList;
    private Context context;
    private OrdemServicoClickListener ordemServicoClickListener;

    public OrdemServicoAdapter (Context context, List<OrdemServicoBean> ordemServicoList,
                                OrdemServicoClickListener ordemServicoClickListener){
        this.context = context;
        this.ordemServicoList = ordemServicoList;
        this.ordemServicoClickListener = ordemServicoClickListener;

    }


    @NonNull
    @Override
    public OrdemServicoAdapter.OrdemServicoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_row_chamado,viewGroup, false);
        OrdemServicoViewHolder holder = new OrdemServicoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdemServicoAdapter.OrdemServicoViewHolder holder, final int position) {
        //Atualiza a view
        OrdemServicoBean osb = ordemServicoList.get(position);

        holder.txtId.setText(String.valueOf(osb.getId()));
        holder.txtNome.setText(osb.getNome());
        holder.txtTitulo.setText(osb.getTitulo());
        holder.txtLocal.setText(osb.getLocal());

        // Click
        if (ordemServicoClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ordemServicoClickListener.onClickOrdemServico(holder.itemView, position);
                }
            });
            // Click longo
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ordemServicoClickListener.onLongClickOrdemServico(holder.itemView, position);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.ordemServicoList != null ? this.ordemServicoList.size():0;
    }

    public interface OrdemServicoClickListener {
        void onClickOrdemServico(View view, int idx);

        void onLongClickOrdemServico(View view, int idx);
    }


    // ViewHolder com as views
    public static class OrdemServicoViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNome;
        public TextView txtTitulo;
        public TextView txtLocal;
        public TextView txtId;
        ProgressBar progress;
        CardView cardView;

        public OrdemServicoViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            txtId = view.findViewById(R.id.activity_consultar_chamado_id);
            txtNome = view.findViewById(R.id.activity_consultar_chamado_nome);
            txtLocal = view.findViewById(R.id.activity_consultar_chamado_local);
            txtTitulo = view.findViewById(R.id.activity_consultar_chamado_titulo);

        }
    }
}
