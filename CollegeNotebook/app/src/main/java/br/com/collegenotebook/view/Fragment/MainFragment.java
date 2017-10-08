package br.com.collegenotebook.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.EditNameDialogListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.BaseController;
import br.com.collegenotebook.controller.MainController;
import br.com.collegenotebook.model.Materia;
import br.com.collegenotebook.view.Activity.GalleryActivity;
import br.com.collegenotebook.view.Adapter.SubjectAdapter;

/**
 * Created by GRodrigues17 on 23/10/2016.
 */

public class MainFragment extends Fragment implements EditNameDialogListener,CreateDirectoryListener{
    private BaseController baseController;
    private SubjectAdapter adapter;
    private List<Materia> materias;
    private ListView materiasListView;
    private MainController mainController;
    private SparseBooleanArray mSelectedItemsIds;
    private FloatingActionButton fab;

    private GalleryFragment galleryFragment;
    private AlbumEmptyFragment albumEmptyFragment;
    DialogFragment dialog;

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
        mainController = new MainController(getContext());
        materiasListView = (ListView) view.findViewById(R.id.list_materia_name);
        readRecords();

        materiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nomeMateria = materias.get(position).getPasta();
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
                        List<Materia> aux = new ArrayList<Materia>();
                        aux.addAll(materias);

                        SparseBooleanArray selected = mSelectedItemsIds;
                        for (int i = 0; i < mSelectedItemsIds.size(); i++) {
                            if (selected.valueAt(i)) {
                                Materia itemSelecionado = materias.get(selected.keyAt(i));
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


        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }


    private void showDialog() {
        // Create an instance of the dialog fragment and show it
        dialog = new NewMateriaDialog();
        dialog.show(getChildFragmentManager(), "NoticeDialogFragment");;
    }


    public void readRecords() {
        materias = baseController.getAll();
        adapter = new SubjectAdapter(getActivity(), materias);
        materiasListView.setAdapter(adapter);
    }


    private void testando(String nomeMateria) {
        albumEmptyFragment = new AlbumEmptyFragment();
        galleryFragment = new GalleryFragment();

        ft = getActivity().getSupportFragmentManager().beginTransaction();
        mainController.criaDiretorio(nomeMateria);

        //Procura o diretório específico da matéria
        File file;
        String root_sd = Environment.getExternalStorageDirectory().toString();
        file = new File( root_sd +"/CollegeNotebook"+ "/" + nomeMateria) ;
        File list[] = file.listFiles();


        //valIdação se há algo dentro do diretório
        if (list.length == 0){
            Toast.makeText(getActivity() , "Naõ tem nada ainda.", Toast.LENGTH_SHORT).show();
            fab.hide();
            //chamo a tela de inserir conteúdo nesse diretório
            enviaMensagemParaOFragment(nomeMateria , albumEmptyFragment);
            ft.replace(R.id.your_placeholder, albumEmptyFragment);
            ft.addToBackStack("empty");
            ft.commit();

        }else {
            //envio por bundle o nome da matéria que quero exibir os dados
            fab.hide();
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
    public void onFinishEditDialog(Materia materia) {
        this.baseController.open();

        this.baseController.insertSubject(materia);
        this.adapter.add(materia);
        this.adapter.notifyDataSetChanged();
        this.readRecords();

        this.baseController.close();

    }

    @Override
    public void
    onCreateMateriaListener(String pastaMateria) {
        boolean dir = Boolean.parseBoolean(mainController.getCaminhoSdCard());
        if (dir) {
            mainController.criaDiretorio(pastaMateria);
        }
        else
            mainController.criaDiretorioInterno(pastaMateria);

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
