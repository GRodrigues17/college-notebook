package br.com.collegenotebook.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.BaseController;
import br.com.collegenotebook.controller.NotebookController;
import br.com.collegenotebook.model.Matter;
import br.com.collegenotebook.view.Activity.GalleryActivity;
import br.com.collegenotebook.view.Adapter.MatterAdapter;

/**
 * Created by GRodrigues17 on 23/10/2016.
 */

public class MainFragment extends Fragment {
    private BaseController baseController;

    private MatterAdapter adapter;
    private List<Matter> matters;
    private ListView materiasListView;
    private NotebookController notebookController;
    private SparseBooleanArray mSelectedItemsIds;


    private GalleryFragment galleryFragment;
    private AlbumEmptyFragment albumEmptyFragment;
    private FragmentTransaction ft;



    public static MainFragment newInstance(int someInt, String someTitle) {
        MainFragment fragmentDemo = new MainFragment();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        baseController = new BaseController(getContext());
        notebookController = new NotebookController(getContext());

        materiasListView = (ListView) view.findViewById(R.id.list_materia_name);
        readRecords();

        materiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nomeMateria = matters.get(position).getFolder();
                Intent intent = new Intent();
                intent.setClass(getActivity(), GalleryActivity.class);
                intent.putExtra("nome_materia", nomeMateria);
                startActivity(intent);
            }
        });

        materiasListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        materiasListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long l, boolean checked) {
                final int checkedCount = materiasListView.getCheckedItemCount();
                actionMode.setTitle("Delete Subjects");
                actionMode.setSubtitle(checkedCount + " selecionados");
                if (checked)
                    mSelectedItemsIds.put(position, checked);

                else
                    mSelectedItemsIds.delete(position);

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.menu_list, menu);
                mSelectedItemsIds = new SparseBooleanArray();
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.delete:
                        List<Matter> aux = new ArrayList<Matter>();
                        aux.addAll(matters);

                        SparseBooleanArray selected = mSelectedItemsIds;
                        for (int i = 0; i < mSelectedItemsIds.size(); i++) {
                            if (selected.valueAt(i)) {
                                Matter itemSelecionado = matters.get(selected.keyAt(i));
                                int pos = aux.indexOf(itemSelecionado);

                                if (pos != -1) {
                                    baseController.deleteSubject(itemSelecionado);

                                }
                            }
                        }

                        readRecords();
                        actionMode.finish();
                        return true;
                    case R.id.deleteAll:
                        for ( int i=0; i < materiasListView.getAdapter().getCount(); i++) {
                            materiasListView.setItemChecked(i, true);
                        }
                        return true;
                    default:
                        return false;


                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

    }

    public void readRecords() {
        matters = baseController.getAll();
        if (matters.size()==0){
            showEmptyNotebook();
        }else {
        adapter = new MatterAdapter(getActivity(), matters);
        materiasListView.setAdapter(adapter);
        }
    }


    public void showEmptyNotebook(){
        NotebookEmptyFragment notebookEmptyFragment = new NotebookEmptyFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentDisplay, notebookEmptyFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void testando(String nomeMateria) {
        albumEmptyFragment = new AlbumEmptyFragment();
        galleryFragment = new GalleryFragment();

        ft = getActivity().getSupportFragmentManager().beginTransaction();
        notebookController.criaDiretorio(nomeMateria);

        //Procura o diretório específico da matéria
        File file;
        String root_sd = Environment.getExternalStorageDirectory().toString();
        file = new File( root_sd +"/Mattercam"+ "/" + nomeMateria) ;
        File list[] = file.listFiles();


        //valIdação se há algo dentro do diretório
        if (list.length == 0){
            Toast.makeText(getActivity() , "Naõ tem nada ainda.", Toast.LENGTH_SHORT).show();
            //chamo a tela de inserir conteúdo nesse diretório
            enviaMensagemParaOFragment(nomeMateria , albumEmptyFragment);
            ft.replace(R.id.your_placeholder, albumEmptyFragment);
            ft.addToBackStack("empty");
            ft.commit();

        }else {
            //envio por bundle o nome da matéria que quero exibir os dados
            enviaMensagemParaOFragment(nomeMateria , galleryFragment);
            ft.replace(R.id.your_placeholder, galleryFragment);
            ft.addToBackStack("gallery");
            ft.commit();
        }
    }

    public void enviaMensagemParaOFragment(String nomeMateria, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("nome_materia", nomeMateria );
        fragment.setArguments(bundle);
    }



    @Override
    public void onResume() {
        baseController.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        baseController.close();
        super.onPause();
    }




}

