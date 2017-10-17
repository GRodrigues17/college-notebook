package br.com.collegenotebook.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.EditNameDialogListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.BaseController;
import br.com.collegenotebook.controller.NotebookController;
import br.com.collegenotebook.model.Matter;
import br.com.collegenotebook.view.Adapter.MatterAdapter;
import br.com.collegenotebook.view.Fragment.CalendarFragment;
import br.com.collegenotebook.view.Fragment.NotebookEmptyFragment;
import br.com.collegenotebook.view.Fragment.NewMateriaDialog;
import br.com.collegenotebook.view.Fragment.NotebookFragment;
import br.com.collegenotebook.view.Fragment.ProfileFragment;
import br.com.collegenotebook.view.Fragment.TimeSheetFragment;

public class MainActivity extends AppCompatActivity implements EditNameDialogListener,CreateDirectoryListener,View.OnClickListener{

    private MatterAdapter adapter;
    private List<Matter> matters;
    private ListView materiasListView;
    private NotebookController notebookController;
    private BaseController baseController;

    private ImageView home;
    private ImageView timeSheet;
    private ImageView calendar;
    private ImageView profile;
    private ImageView add;

    private TimeSheetFragment timeSheetFragment;
    private CalendarFragment calendarFragment;
    private ProfileFragment profileFragment;
    private NotebookFragment notebookFragment;
    private NotebookEmptyFragment notebookEmptyFragment;

    private DialogFragment dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        baseController = new BaseController(this);
        notebookController = new NotebookController(this);
        if (findViewById(R.id.fragmentDisplay) != null) {
            if (savedInstanceState != null) {
                return;
            }

            notebookFragment = new NotebookFragment();
            notebookFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentDisplay, notebookFragment).commit();
        }

        home = (ImageView) findViewById(R.id.home);
        timeSheet = (ImageView) findViewById(R.id.timesheet);
        calendar = (ImageView) findViewById(R.id.calendar);
        profile = (ImageView) findViewById(R.id.profile);
        add = (ImageView) findViewById(R.id.add);


        home.setOnClickListener(this);
        calendar.setOnClickListener(this);
        timeSheet.setOnClickListener(this);
        add.setOnClickListener(this);
        profile.setOnClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            //TODO verificar se tem memória para armazenar mesmo a foto

            //verifico se tirei minha foto
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Imagem salva em SDCard/CollegeNotebook", Toast.LENGTH_SHORT).show();
                //dao.salva(localDaFoto);
            }else{
                Toast.makeText(this, "Não foi possível tirar foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showDialog() {
        // Create an instance of the dialog fragment and show it
        dialog = new NewMateriaDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }



    @Override
    public void
    onCreateMateriaListener(String pastaMateria) {
        boolean dir = Boolean.parseBoolean(notebookController.getCaminhoSdCard());
        if (dir) {
            notebookController.criaDiretorio(pastaMateria);
        }
        else
            notebookController.criaDiretorioInterno(pastaMateria);

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.home:
                showNotebooks();
                break;
            case R.id.timesheet:
                showTimesheet();
                break;
            case R.id.add:
                showDialog();
                break;
            case R.id.calendar:
                showCalendar();
                break;
            case R.id.profile:
                showProfile();
                break;
        }


    }

    public void showCalendar(){
        calendarFragment = new CalendarFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentDisplay, calendarFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void showTimesheet(){
        timeSheetFragment =  new TimeSheetFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentDisplay, timeSheetFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void showProfile(){
        profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentDisplay, profileFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showNotebooks(){
        notebookFragment = new NotebookFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentDisplay, notebookFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFinishEditDialog(Matter matter) {
        baseController.open();
        baseController.insertSubject(matter);
        showNotebooks();
        baseController.close();

    }


}
