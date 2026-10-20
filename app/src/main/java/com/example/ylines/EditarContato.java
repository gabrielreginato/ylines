package com.example.ylines;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EditarContato extends AppCompatActivity {

    Button btnVoltar;

    ImageButton btnCamera;
    ImageButton btnGaleria;

    ImageView imgPerfil;

    EditText edtTxtNome;
    EditText edtTxtTelefone;
    EditText edtTxtEndereco;
    EditText edtTxtCidade;
    EditText edtTxtUf;

    Button btnSalvar;

    int id;

    String nome;
    String telefone;
    String endereco;
    String cidade;
    String uf;
    String imagemPerfilPath;

    private Uri fotoCameraUri;
    private String caminhoFotoCamera;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_contato);

        btnVoltar = findViewById(R.id.btnVoltar);

        btnCamera = findViewById(R.id.btnCamera);
        btnGaleria = findViewById(R.id.btnGaleria);

        imgPerfil = findViewById(R.id.imageView);

        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtTelefone = findViewById(R.id.edtTxtTelefone);
        edtTxtEndereco = findViewById(R.id.edtTxtEndereco);
        edtTxtCidade = findViewById(R.id.edtTxtCidade);
        edtTxtUf = findViewById(R.id.edtTxtUf);

        btnSalvar = findViewById(R.id.btnSalvar);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);

        nome = intent.getStringExtra("nome");
        telefone = intent.getStringExtra("telefone");
        endereco = intent.getStringExtra("endereco");
        cidade = intent.getStringExtra("cidade");
        uf = intent.getStringExtra("uf");

        imagemPerfilPath =
                intent.getStringExtra("imagemPerfilPath");

        edtTxtNome.setText(nome);
        edtTxtTelefone.setText(telefone);
        edtTxtEndereco.setText(endereco);
        edtTxtCidade.setText(cidade);
        edtTxtUf.setText(uf);

        carregarImagem();

        btnVoltar.setOnClickListener(v -> {
            finish();
        });

        btnGaleria.setOnClickListener(v -> {

            selecionarImagem.launch(
                    new PickVisualMediaRequest.Builder()
                            .setMediaType(
                                    ActivityResultContracts
                                            .PickVisualMedia
                                            .ImageOnly.INSTANCE
                            )
                            .build()
            );
        });

        btnCamera.setOnClickListener(v -> {
            abrirCamera();
        });

        btnSalvar.setOnClickListener(v -> {

            dbHelper.atualizarContato(
                    id,
                    edtTxtNome.getText().toString(),
                    edtTxtTelefone.getText().toString(),
                    edtTxtEndereco.getText().toString(),
                    edtTxtCidade.getText().toString(),
                    edtTxtUf.getText().toString(),
                    imagemPerfilPath != null
                            ? imagemPerfilPath
                            : ""
            );

            setResult(RESULT_OK);

            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }

    private void carregarImagem() {

        if (imagemPerfilPath == null ||
                imagemPerfilPath.isEmpty()) {

            return;
        }

        File arquivo =
                new File(imagemPerfilPath);

        if (arquivo.exists()) {

            imgPerfil.setImageURI(
                    Uri.fromFile(arquivo)
            );
        }
    }

    private final ActivityResultLauncher<PickVisualMediaRequest>
            selecionarImagem = registerForActivityResult(
                    new ActivityResultContracts.PickVisualMedia(),
                    uri -> {

                        if (uri != null) {

                            String caminho =
                                    copiarImagemParaAplicacao(uri);

                            if (caminho != null) {

                                imagemPerfilPath = caminho;

                                imgPerfil.setImageURI(
                                        Uri.fromFile(
                                                new File(caminho)
                                        )
                                );

                            } else {

                                Toast.makeText(
                                        EditarContato.this,
                                        "Erro ao salvar imagem",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                        } else {

                            Toast.makeText(
                                    EditarContato.this,
                                    "Nenhuma imagem selecionada",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );

    private String copiarImagemParaAplicacao(Uri uri) {

        File diretorio = new File(
                getExternalFilesDir(
                        Environment.DIRECTORY_PICTURES
                ),
                "contatos"
        );

        if (!diretorio.exists()) {

            if (!diretorio.mkdirs()) {
                return null;
            }
        }

        String nomeArquivo =
                "contato_" +
                        System.currentTimeMillis() +
                        ".jpg";

        File arquivoDestino =
                new File(diretorio, nomeArquivo);

        try (
                InputStream inputStream =
                        getContentResolver()
                                .openInputStream(uri);

                OutputStream outputStream =
                        new FileOutputStream(
                                arquivoDestino
                        )
        ) {

            if (inputStream == null) {
                return null;
            }

            byte[] buffer =
                    new byte[8192];

            int quantidade;

            while (
                    (quantidade =
                            inputStream.read(buffer)) != -1
            ) {

                outputStream.write(
                        buffer,
                        0,
                        quantidade
                );
            }

            outputStream.flush();

            return arquivoDestino.getAbsolutePath();

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }

    private final ActivityResultLauncher<Intent>
            tirarFoto =

            registerForActivityResult(
                    new ActivityResultContracts
                            .StartActivityForResult(),
                    resultado -> {

                        if (resultado.getResultCode()
                                == RESULT_OK) {

                            /*
                             * A câmera já salvou a imagem
                             * no arquivo criado em abrirCamera().
                             */
                            imagemPerfilPath =
                                    caminhoFotoCamera;

                            imgPerfil.setImageURI(
                                    Uri.fromFile(
                                            new File(
                                                    caminhoFotoCamera
                                            )
                                    )
                            );

                        } else {

                            Toast.makeText(
                                    EditarContato.this,
                                    "Foto cancelada",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );

    private void abrirCamera() {

        File diretorio = new File(
                getExternalFilesDir(
                        Environment.DIRECTORY_PICTURES
                ),
                "contatos"
        );

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(
                diretorio,
                "contato_" +
                        System.currentTimeMillis() +
                        ".jpg"
        );

        caminhoFotoCamera =
                arquivo.getAbsolutePath();

        fotoCameraUri =
                FileProvider.getUriForFile(
                        this,
                        getPackageName()
                                + ".fileprovider",
                        arquivo
                );

        Intent intent =
                new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE
                );

        intent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                fotoCameraUri
        );

        intent.addFlags(
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
        );

        tirarFoto.launch(intent);
    }
}