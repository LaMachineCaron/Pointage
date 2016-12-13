package gae.pointage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import gae.pointage.bdd.Joueur;
import gae.pointage.bdd.Penalite;

/**
 * Created by Alexandre on 2016-11-14.
 */
public class JoueurAdapter extends BaseAdapter {

    private main context;
    private int layoutId;
    private List<Joueur> pairs;

    public JoueurAdapter(main context,
                         List<Joueur> pairs,
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
        Joueur joueur = this.pairs.get(position);

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

        Penalite penalite = null;
        for(Penalite p : context.penalitesEnCours) {
            if (p.getJoueur().getId() == joueur.getId()) {
                penalite = p;
            }
        }
        if (penalite != null) {
            long tempsPeriodeTotal = (context.chrono.periode - 1) * context.TEMPS_PERIODE;
            long tempsFinPenalite = penalite.getTempsDebut() + tempsPeriodeTotal + penalite.getInfraction().getTemps();
            long tempsPenaliteRestant = tempsFinPenalite - context.chrono.tempsPasse;
            System.out.println(tempsPenaliteRestant);
            System.out.println(viewHolder.textviewTempsPenalite.getVisibility());
            if (tempsPenaliteRestant < 0) { // Penalite finie
                viewHolder.textviewTempsPenalite.setVisibility(View.INVISIBLE);
            } else { // Penalite en cours
                viewHolder.textviewTempsPenalite.setVisibility(View.VISIBLE);
                viewHolder.textviewTempsPenalite.setText(Utilities.formatterTemps(tempsPenaliteRestant));
            }
        }

        viewHolder.textviewNumeroJoueur.setText(joueur.getNumero());
        viewHolder.textviewNomJoueur.setText(joueur.getNom());
        if (joueur.isEstGardien()) {
            viewHolder.textviewNomJoueur.setTextColor(context.getColor(android.R.color.holo_blue_bright));
        } else {
            viewHolder.textviewNomJoueur.setTextColor(context.getColor(android.R.color.black));
        }

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
