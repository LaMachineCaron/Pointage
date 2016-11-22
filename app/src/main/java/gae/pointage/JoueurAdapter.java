package gae.pointage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alexandre on 2016-11-14.
 */
public class JoueurAdapter extends BaseAdapter {

    private main context;
    private int layoutId;
    private ArrayList<Joueur> pairs;

    public JoueurAdapter(main context,
                         ArrayList<Joueur> pairs,
                         int layoutId) {
        this.context = context;
        this.pairs = pairs;
        this.layoutId = layoutId;

    }

    @Override
    public int getCount() {
        return pairs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(
                    this.layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.textviewNomJoueur = (TextView) convertView.findViewById(R.id.textview_nom_joueur);
            viewHolder.textviewNumeroJoueur = (TextView) convertView.findViewById(R.id.textview_numero_joueur);
            viewHolder.textviewTempsPenalite = (TextView) convertView.findViewById(R.id.textview_temps_penalite);
            viewHolder.buttonBut = (Button) convertView.findViewById(R.id.button_but);
            viewHolder.buttonPenalite = (Button) convertView.findViewById(R.id.button_penalite);
            viewHolder.buttonAssist = (Button) convertView.findViewById(R.id.button_assist);

            /**
             * At very first time when the List View row Item control's
             * instance is created it will be store in the convertView as a
             * ViewHolder Class object for the reusability purpose
             **/
            convertView.setTag(viewHolder);
        } else {
            /**
             * Once the instance of the row item's control it will use from
             * already created controls which are stored in convertView as a
             * ViewHolder Instance
             * */
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textviewNumeroJoueur.setText(Integer.toString(this.pairs.get(position).numero));
        viewHolder.textviewNomJoueur.setText(this.pairs.get(position).nom);
        viewHolder.textviewTempsPenalite.setText(Utilities.formatterTemps(this.pairs.get(position).temps_penalite_restant));
        viewHolder.buttonBut.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                context.but(pairs.get(position));
            }
        });

        viewHolder.buttonPenalite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                context.penalite(pairs.get(position));
            }
        });

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.buttonAssist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                context.assist(pairs.get(position));
                if (finalViewHolder.buttonAssist.isPressed()){
                    finalViewHolder.buttonAssist.setPressed(false);
                    context.retirerAssist(pairs.get(position));
                } else {
                    finalViewHolder.buttonAssist.setPressed(true);
                    context.ajouterAssist(pairs.get(position));
                }
            }
        });


        return convertView;
    }

    public class ViewHolder {
        public TextView textviewNumeroJoueur;
        public TextView textviewNomJoueur;
        public Button buttonBut;
        public Button buttonPenalite;
        public TextView textviewTempsPenalite;
        public Button buttonAssist;
    }
}
