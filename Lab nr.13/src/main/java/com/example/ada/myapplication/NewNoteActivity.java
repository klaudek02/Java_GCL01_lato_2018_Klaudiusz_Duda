package com.example.klaudiusz.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNoteActivity extends Activity {
    public EditText textSubject;
    public EditText textDescription;
    public Button buttonAddPhoto;
    public Button buttonSaveNote;
    String photopath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
    File imageFile = new File(photopath);
    Uri imageFileUri = Uri.fromFile(imageFile); // convert path to Uri
    ImageView iv;
    private Database db;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_RESULT = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        textSubject = (EditText) findViewById(R.id.textSubject);
        textDescription = (EditText) findViewById(R.id.textDescription);
        buttonAddPhoto = (Button) findViewById(R.id.buttonAddPhoto);
        buttonSaveNote = (Button) findViewById(R.id.buttonSaveNote);
        db = new Database(this);
        initBtnOnClickListeners();
    }

    private void initBtnOnClickListeners() {
        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }

        });
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote()
    {
        String subject = textSubject.getText().toString();
        String description = textDescription.getText().toString();
        String photo = "NULL";
        photo = setImage();
        if(!isEmpty(subject))
        {
            db.addNote(new Note(description,subject,photo));
            finish();
        }
    }
    private boolean isEmpty(String etText) {
        if (etText.trim().length() > 0)
            return false;
        return true;
    }
    private String setImage()
    {
        return "/9j/4AAQSkZJRgABAQEAYABgAAD/4QE2RXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAAB\n" +
                "AIaSAgABAQAALAAAAAAAAAD4X+kDhAIAAIQCAADgBAAAGAMAAOAEAAAgCgAAYAQAAGAEAAAgCgAA\n" +
                "AAoAAEQEAAAMBAAARAQAAAAKAACoDAAACAUAAHwEAAB8BAAACAUAAKgMAACsDwAAcAUAAIAEAABQ\n" +
                "BAAAgAQAAHAFAACsDwAArh4AAPgHAADQBQAAEAUAABAFAADQBQAA+AcAAK4eAACoDwAAcAgAAHgG\n" +
                "AADoBQAAeAYAAHAIAACoDwAAmBAAAGAJAACYBwAAmAcAAGAJAACYEAAAeBIAABALAACoCQAAEAsA\n" +
                "AHgSAACoFQAAEA4AABAOAACoFQAAqBsAAHAUAACoGwAAICgAACAoAADATgAAAAD/2wBDAAUEBAQE\n" +
                "AwUEBAQGBQUGCA0ICAcHCBALDAkNExAUExIQEhIUFx0ZFBYcFhISGiMaHB4fISEhFBkkJyQgJh0g\n" +
                "ISD/2wBDAQUGBggHCA8ICA8gFRIVICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg\n" +
                "ICAgICAgICAgICAgICD/wAARCAEsAUwDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAEC\n" +
                "AwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0Kx\n" +
                "wRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1\n" +
                "dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ\n" +
                "2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QA\n" +
                "tREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYk\n" +
                "NOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaH\n" +
                "iImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq\n" +
                "8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDhlTmpVT1oRe9TKBXpHw4gTJp4Tnk0v0p4z3oEIAcVIFzS\n" +
                "gcdKkUCgYipinqmaUDpTwD+FMm43FO28U4Dnmn7aLgQ7aCtS7aNuRVpgQFPamlPwqxt4xQUzWxJW\n" +
                "2UbetTEc4pdtMRCFNNKVYxx70m3jpQBX2UuwVNt9KNtAyHZz7UbMdRU22jac0gIgtJsNT7aTbSGi\n" +
                "AofSm7TVkimFaLgVmXioihIq4UOKjKelMCmVqNkzVwpUTJzVphqUnSoSlXmXFQutXcCky1Ey1cZc\n" +
                "dqhZc0xFUimFRjirDLUWOCKdwKxXmoyvqKsstRstSwK5Wo2X2qyVNRstIZVZTUZXmrLLURXmkxHc\n" +
                "qKUUKMVIMEdK4TRAq1MFwKYg5qZR70AxVWnhacO3GacB7UEiBefanheelKBxTsY70xBijFOxTgD1\n" +
                "NA7jMEdqNp9KlwKXaO9WibkO3mgipdoPbml28VpcCDZ7Um2pyMjrRtFO4EG2jbU20ZpdvtRzDsV9\n" +
                "tBQ/WtW00XU9QYCzspZ2LqgCL/Eeg/Gtu0+Hviq6kaL+x7iORU80pIu0lc44zwT7Vm6iRrGlKWyO\n" +
                "P2cUhSvXR8FNbe23LdQiTPAYkDGep/D+VE3wR16MxrBe20+9MsxyojbPT3GO9ZutHudCwda1+Vnk\n" +
                "W32pdvoK9Gu/hL4stjtS2W4+cJmM8chTn6DLf98+9SaZ8IfE98Ee5iWyDqWHmdRzxkCj2q7kfVql\n" +
                "7crPNCvtTdvfBr3BfgjNFpuZL8S3JAZlA6Yydq/U45ri774beI7AyS3dmUjDkAryCAMliew7DvSj\n" +
                "UT6jnhasFeUTgSPWo2StO7sntpCj8sMZx0GaplDnG01qmc7iVCuKjKg9qsspB6VCwxWqFYrOtQMt\n" +
                "W3XIqFlq0JlVl68VCy1bIqF19qokqEVCVq0w55qJlFMCsR2qNl/CrJUdKjZaBlcjFRlTVgoc1GVq\n" +
                "bkldlzURUZ61aK1ERz0pDR2IB7dKlUcVGOBxUimuIu5KoxUoHPSo161KOlArkiinDrTF55qUe9Ar\n" +
                "jhS4pAKkUA9OaAuIF4qQDilC5pwXirFcaBTgOcU6jaaYhpGKMZp4Qn3qxDaSyypGkTuWPRFyf/1+\n" +
                "1O9ikiosZY4AJNasPhvXJ5I44tJu380AoyxEqwPQ56V6P4K+HL3rJc6nAGtSeVdSkiEensf617Zp\n" +
                "+nWemWUdnZQiKCP7qA5ArnqV+XRHqYbASqq8tEfPGlfCXxTfBZJ7aKzRlz+/fB+mByK9L0j4Q+Gb\n" +
                "OzRdSia+uAd28sVA9gAen1r0eiuWVWUj2KWAo091f1MvS9A0nRYGg02ySCNmDFQSRkADv06CtTFG\n" +
                "aKx5rvU7VFRVkgoooplBRRRQAVFcW0F3C0NxEssbcFWGQalooE1fc5DW/h9oOr2fkpbraSFy5ljX\n" +
                "5iT/AJ79K89134MXKiNdFulaLHziThmOOWJ6n2UV7jRWkako7M5amEpVN1Y+Pta8G6zokhjvICCo\n" +
                "y7D7iD3bp/nvXMyR7SRwR619q6xomna5p8llqFus0Tdj2PrXmV38FLN3Z4NR3n+BZI/lU+px1x2H\n" +
                "FdcMQvtHkVsunF+5qj5wZTjIqF0IJyDmvoW++DyaZpobTt1/fyZ3TS4Cp9BXlmq+FJrR7m1gQ3D2\n" +
                "y757jkID6D8K6I1YvY4amHnDSSOFYHmomBIrRktpBkhCVzgNjg1VkiK4yOtbqRzOLKTioSMGrLjn\n" +
                "GKhYVVxNEBphFSGmmi4iIioyPapzTCDipbCxAw9qiI5qyy1GV56VHMh2OoXHepAPeo1qQfdrBokm\n" +
                "FSKKhXk5JqUdKAJR1xUw6VCvSpVxikA/HvUqj0qMCpRSuOw8dKeKdDGGYK3Geldponw+1rWvKlt7\n" +
                "V47djtZ5RgD3B7j3FDmkXCm5u0UcxaaXd3hH2W3lnB4zGm7B+grv9G+E+qalYJdG8igzyFljZSfY\n" +
                "jqPrXqfhPwTb+G4mMjxTzP1KR7R+PJz9a64AAYA4rnlXf2T28Plqter9x5RZ/By0CJ9svfmByTEC\n" +
                "rfgf/rYPpXcaT4R0jTLaON7eO8ljPyzzxqX/ADxXQUVg6knuz06eFpU3eKAADoMUUZFJms7nULR3\n" +
                "ppOOaCRjNS5AOo+tMDg5x2pc56VPOug7DqKaG4pQeOatSuIWikzTWcDuKTkluA+ioRKvenq4Peoj\n" +
                "VTHYfRRnimljnGOK0uIdRTQ4PTmoGu442xJ8o9TwKHJLcCyQCMGsy40HR7m1ltZtPhaKU5ddv3vr\n" +
                "WhHLHKu6N1cexzT6pPqiXFS3R5N4j+EWm3Ectxp8k5ZjlYQwwv045rwnxN4butFuZBcwNAAcBWyG\n" +
                "I9ea+z6wdc8K6Pr2JL60WSZFIRj0H1HQ1vCs1uebXwMZK8ND4ffaTxgfjVZ1r3Pxx8O9as5me0iW\n" +
                "e3xy0duABXjt/p9zbzMrwMpHXgf0rsjVueHUoyi7MyGFRkVYdSGwRUZAzWvtDn5SEjimmpCD6U0r\n" +
                "WbmCREQe1NIOalxTSDmsnMpHQAVIoNKqYAqRVpuZnygoJ7VMvakC+lSooqXMdhyj2p4HYUBcdKlR\n" +
                "MnGKh1CrCoCTXRaH4avNbnENoSrdy68D39ak8MeF73XtQjghtZZIyQGdDgIPc9q+hfDngDQ/D/lz\n" +
                "xxyT3SgfPK5bB9h0rOUzsw+ElWemxg+EvhVpWmJDe6wBfXincFx+6Hocd/xr0pESNAiKFVRgADAF\n" +
                "LwKTcM4zXPKfc+lpUYUo2gh1FISB1NRTXCRISeSBwB3qXJLVmxI7rGhdzhR1JrBvPEtpDkxtlF+8\n" +
                "5+UD35rI8e65Npnh9WiuxZtIcFgu6Q+yr6/XpXh9nrd7qHiGGzt47ppFw0kscv71T7n0/OvOrYiT\n" +
                "lyQNYw0uz6Tsrw3MYlEismOowf8AP61bM2GUEZDdweK5TRLqRbKOJpVmC8vnCuvuQOP5VvQy7vus\n" +
                "HT2PINbRlpYzuX0lD/MvK9PcGo7mXZCzqRwOaEYqMMRzz9abII2BJHHII/xqp3cbDW5VgvMkBwVJ\n" +
                "bjPf2qxJcYfC8nGaoNGsETQsQ0Y+Zcckj0+oqjJqOVQh8uMAn+8PX8/51wpunHlbLeuptm5ATfkc\n" +
                "nGKlW4BjViaxopBPbHa2AeufTt+VAeVR5TnKsOCK6oSbRDNd7nbEG9eKpC83s0itvBfGPwqrLcj7\n" +
                "OhLEIMsT6gVRWUCwt1DHcwMhI96zqtvQcTcEoFuQTzgkd6kFxsgV3O30HrWEboi5WIPtGG6Hr6VJ\n" +
                "HdLOXdugXt9eBWUZFnQxXG9cn0qfOR0rChvVjfZkZ649BWrDPvXJIrtpTvozNof5w37EUnHBPvWJ\n" +
                "4mk1BbA/YLeE8EySSsQUAH8IA+Y/Uge9bmTtwmB7kVTvbdJrZ4pDGdww3y8H261s1dWIbPKtG8ct\n" +
                "p2qLFOzLGx2yBfmwfp2/zzXsdrcRXVrHcQsGjkUMCO9fOvjHTdM0fxBCLP7VNO7b/kVVVD+RwPx4\n" +
                "r1D4c+IjqGlfY7lv38Z4BxnH4ehyK5KUnTqcr2Zp8Suj0CkBBozzVK7kngYSRIHU/eBOMV2ylyq5\n" +
                "KLcsSTRNE4yrDBHtXhPxJ+GtzEsuo6JGDbEZkjwB5fHXPU17lDcpKoP3SexqSSOOaNo5UDowwVYZ\n" +
                "BFXCaeqOevQjVVnufA93ZTW8hWRRnPUdDVJkI5xxX1J48+EEGptJqGiIiEDJthkZPtXzjqel3em3\n" +
                "klrdW0kMiMVKuuCK29rbc+drYeVN2aMUpTSvbFWCmDTWU4qHVRzWKxXA6UzbVgrxTNtZuqOx0gTv\n" +
                "UgSpAvTipAlDrEWGKuDUyp7UqpVmJEPD5Ge47VDrAokSRknpXa+EPA954ln+WJ1hHLOW2r+eK3Ph\n" +
                "t4M03XdQe6upXuLe35aMxYBOemc19AW9tb2cCwW0KQxLwERQAKcW3qz08LgvaLnlsZXhvw5ZeG9L\n" +
                "WztVG48u+OWNbLMBSkgCqs0h2nLhB+tE52Wh78IKK5YiS3Khiu5Tjrz0oikJfcV2g9B1NZ0cqTTF\n" +
                "IgI405JAyc/X1qw08du3lICzBck/3R9a44y15mzW3QklucONy84JwewqksoLMzHJX5nZgeSccfh/\n" +
                "nvWbbzNcmfUtsjB0Ij3dCucDA9z0/wDr0zUJZINHcq5eWfK56buwUevQ8+5rH2l/eHboeZfEbVlv\n" +
                "XicMHUxmY9fuHhR7bj27jNebeAYYri/e8mnjMkknDCPt7ll2/qa1/ihfC2sZ5oivm/uokx93K8DH\n" +
                "sDniovAbvBbQBoFllxgGFdhz9QCf1rCnrLmZU9FZHu9ixMURkiY7VGJmjAI/EE4rZjMiqknmFoz1\n" +
                "P3gfxHSubsJ3EKmWMp/vSYYfmBmti2vIgcpIcjggrgkfj1rq5kYI11uSigD94g6jPI9xVO4uZYl8\n" +
                "yFw6LwVbqPaq099bqd28gHPAH+eazXu3iBkilWSNuquOaznO5aJJtTZJ9oY7ZDgA/wALeh+vrVPz\n" +
                "yfmL8ZLj0PNV7xo7hHDODE/KtnlTVGR3hjKzbgwPIPbPf6ZrldzQ3rbURHeSKjZygkCg9V9f1rdi\n" +
                "uYmWEZDjaM15ylwIYt+P3kIZcd9pOfxGK2oL2VEiBYYZQM59z/8AWrejUcTOaN7UHX7FKgIJCsR9\n" +
                "OlVvMEBjkcgBI1Ug9CccYqo1ylwgiZsBn2/Udaq31wGsjGrnLZ+bPT5a3k09SE+hBcag6xpcoxGV\n" +
                "IBPqTVyO/C2zvIR6gZ649q5iS4LSx2SnK8MeOmKd9oeSVmdtkKnk9yBxgfjmuPW90baWOvt7xExc\n" +
                "TFRIwPAbPSta21FpGDAkA8gkcAVxtukkivdXJWPIyFfgKPer0bTSLlJyqfxO3GR6D2/KuiDaM2zu\n" +
                "ormOVSN/GfrS3DBIT8oYdeMjP6GuYsb5VfylnBYdl7VqSOXQGTbtPJyQxb+VdtOd1qQzy34ku81r\n" +
                "J5Nw6quC0QmEK/U44J+pJ47Vh+AdemjuPOuCj732yqMg47OD+ufr6V2njKxF5YuI3ljIU7WMgGPo\n" +
                "B0/AV4p4TtL/AEPxHc2Upea3kfzYiXCkOPm7nODg9ea5q6ady6T3R9iW1wJbRJvUDPNSTRpMmxvw\n" +
                "IPNc14PujNpjW0kqyiMDHzAkqRlf0NbF1draKgbJXO3n0PSuyMrxuJ6FXzWs7jbIHCE43Dn8xWvD\n" +
                "KjqNrA1jXV158WU8vzFGeRncKr6ZqL/aHVxsQHAQj7p+voazjaEh3udPxXi3xU+Gsl/BNruiqPMU\n" +
                "bpoecn1Ir2JZw8W5Pm+lCyiWJhkHg10Oz0MKtKNSNmfB9zbNDIUfAwccVVKj0r0v4gWtrH4gvHt1\n" +
                "tyFlYPGiYKnNedyKCxIGBXmzq2dj52ULOxTK5qPb7VaK4phTms/bE2OoEdSqhx6U9VqRVqHXMeUY\n" +
                "qd6vWMEMlzGJ2YIT/D3/AMKrhcVat/lkV2OAPfGfao9sXGJ9I+A0srXw4sdsYRzkxwx7dp/mT7mu\n" +
                "lku1VwoGT3rzX4dyRR2Ukr3HmXDAjYo4j78n1rZ1HVzYwzTysRg4RRjOf8a7HXtBH02E96mjpri/\n" +
                "YEpHgkdeen41i3VwZNn2iZmLdFThRz1+n1rGTUZhaCa/KrJMQBF2Uf3T6nuaqrLNc3DgSEMPmd34\n" +
                "Cjtn/D6VhOo5HakdWl6lvbyiPZEka7snoo7k+5qqbsyWjMzEQyNjc3VyR/nj61ixsn2bznYywkhl\n" +
                "Rz8pxwGb8uBT47h3gMskreVACxkI4JPGR/T2HvU87Cx0FqwlDlx/d69FIzgD1xn9DVHxHP5FpERt\n" +
                "QRxlyjYxypwPrnH61aspNtjaSSx+U0qBwjdRnG0H3x/M1zviu5R5ts+Wjddu4N93J2n9Ca1m7UyV\n" +
                "ufP/AMRWNzqdjaLudEZWYh9u7C9euec11ngmO2KItuIIpc4LNvBP/AgM1xHisvN46VWcKfJUFTkE\n" +
                "H6jOD+Feo+D1kjtE3SHaBjJmDgj06ZH41jTCex6RYxzJGp80EHoS5YEfjzReJCVJJVD0yq5/kagj\n" +
                "kfygRGCCMfK+38gM1SkmuELGO3MhH95iSPx4NazeljJDHBYlIp0JH8LscH6dx/Kq0krAFXZrd+is\n" +
                "eVP9DUha6uDkWsLf7xBYfiOf0pDGu10aN0fJO3cCPfHqKw5SrlJ3kCSLIMEHselV2nneNbeSMSMF\n" +
                "JBz99fY+vtRPJApDqGjwNuzOQp+lLJEYUgkQnYsgZiTyq/1pchSYTAGJBvIONv8AunqCadDemXT5\n" +
                "YXBDhSFPuMVmXF35U723JDgsB6Y/z+tRm4SIOI5CdrB1ycnkY5pxeoS2NwXpD2m4ADaOfUkVTn1A\n" +
                "8x9eMAD071nxlo7QFpNxQLgn2J5/I1E+JriSTOGZNnXitDNEsV2sN68j5Z3IA78en6VYjYysYpWE\n" +
                "axvknPXvtH4VjElo45SP4gAQfrjNStfRpC1w5+aQxsF744H4ev4VMFqW3odDb6nbvekK2FiAzn5s\n" +
                "Ht+Nb8cjyrnZ5gPRpGIA9K83sL2GN5WhdGdyZAT95mPofStDTtfWCYJesHZjyvOPzyK3uo7kWvsd\n" +
                "t5hQ/NNGzL1EQwF/z6mtWG4OxfKgDEj7xbkVW0q40vUowIXXJH3d24A/UHNazWMwtwkLIY16CPP6\n" +
                "knJrppwvqjN+ZzHiW5a3sJHmMqkjAMbAN/jj8a+cnvoY/iJBM8zMHyMu24lgcgce2fevpO/89I5I\n" +
                "GtA3HPzkn+ZNfOHjbTAni+yvlV4mW4XIZuDzj86xxF7GlF6n0P8ADy/eCe0tQeoaBg3dVJII9eDX\n" +
                "oWsQi50+ZVOQUO3HVWHII/HFePeCtRC63YSj7vmbee4Kpyfy/SvX7wmGMRYLI7Mg/EZx/P8AStcP\n" +
                "rBoqb1OSFzIhtnLbZSMEdA2eM+3oR9KhXVHg1Ao+7y5RlWHXjuPUj071DqOGdoZgcKnIzzhj/j/S\n" +
                "sGW8Z0ljkkDEhZonxyGHX+tDViEzvItdMUI8+QL5g/1ijuOhFbdldfb7QycJI0ZG5eQSehryt9WX\n" +
                "7B5UrcKQxI6geo9ua7Xw7MY7G5jdgAikqwPHTOfpWkZX0Jbsj528ZIYtcn53AuyH/ZYHkf1/GuRZ\n" +
                "K7nx5LHceIbibZ5Uzt86jBBI4zkVxrLXztarabR4MldlNkqIoKuMvFRFOelYe2J5TqAuO1ShOKcF\n" +
                "9qkA4qXWM+UYFFTRqdwOcY7+lCp3NSKuG461m6w7HongG8EV4Y5HCqBwh5IB6k+n+H1re12TbeBs\n" +
                "jCcqW6Jn+LHc+lecaNfLYTYQ7i7AsP73t713d6/9pac1wB5kiHbz/P8Ap+Br0KdZTppdUevgaiXu\n" +
                "Mow3gmu2mckuudoP8A6ljnvjoPxNWDdmK3kQyATSJk9giZBH09T3PFc/LI1qVjX5sN8xJxvbqSfY\n" +
                "f0HrTYrsTX7RPIHVSZpmz989Bn2z/KuhSutD1zpFu5biZLYny42IWMf3VA4JH5n6n2rdtZhdwLCQ\n" +
                "AkrFsseAoAA/nj8a42yuV+2B8bd2MsTzjGT+n8663Sk/ex7wxCtlgOigYIA9s/8AoNVHViZ0NzOm\n" +
                "6JgcsnKn09/5Vy3ieSKIR5cbWt8DPXnJz+groZ8PCqY+dlKjjpnp+PP6Vw/jm8zdvEu3EMZKkHAI\n" +
                "G4FT+GP19K1q7akxPFb51PjTU5fMSU/aMHG3PAHrXo3hsW5RAytGD1yqtmvIdEn+06g9yVJ82Qv8\n" +
                "2O59xXtGg2we3UF0QYH3Qefw4rKDuEzrovIZQqLIwxglY+B+YrSt9MD5lN64GOAifzxSadZxoVIS\n" +
                "dxgBt8rc/QE1uSTW9rbAyoluuOMvuLfhiuyFNbs52zHvo0hjZ9hJT+ILk/XGa5a/1GNS6Lc7GU/x\n" +
                "KRj9a0dY1seW4tpYJEHJXzcMo9cf/WrzLXdeSUSyQEeYhwcHBz7HlawqN/ZRrCJ0NzNbzHBfaX+V\n" +
                "grHg44I9aWy1JWtQjyGREjGQBg45Hf1/SvNX8STahayRzBRNEAQT91s9Cfyq3purZO9pFPyHkPu+\n" +
                "brjn0NZKp0ZpynYXMzG3aQDdhgiZPJXPWoYZW3lX5TAyx9OP1rPe8eUkMNrbMLjp0q1a7nuWAQ7B\n" +
                "heR99j1/D/Gp3dwextIBLA7qRleSBzkciqTzsGDR8EOBgdv8kVetI3iKSEEb8AjHUDNRiwUlmRMA\n" +
                "MzA+xAOD9DmtkmzHZmS12IJWRslUbK44AHXH5c1zmq3iyQyq7up6Kd38PHH8vzrWu7bbPNG+7n7r\n" +
                "dwR7e1c3qUbLAB5ZPPzAjuKlNmqRmJrE1nBc3EU5cqcIp/hPIHXpU+j6kJpBPeT+ZK7EklgAvsP6\n" +
                "9+tcD4k1yO10q5bILjoM89f8c1xekeN7+2Mco0yG8iXd8rkZbA54weg9q1jTlLVK49FufYGka9pc\n" +
                "Cqw1G2iGeBJJg/kP5c16fpWvwOUinlEhYDDkED8DjP518g+GPi/4NkRbbUPD02jmQgPc2b71HuVz\n" +
                "kfgCDnpxXuNn4m8P6xoLal4c1wapFARvhSTMkX+1sJ3Af7vT0reHNDpYwmj1LxDDBdWhlTD44BLl\n" +
                "cHuNw/lXzz4/soljacIISDuBDEjI9yK9WXXzLpyywziRmXBzzn8ev8x7V5d41u5bmznSUvtYkHAw\n" +
                "PzpYiacbomndSOo8HXiypZ5cNGpV9+eoKjj164/I17vdyk2uefu5HsVwVr5N+HWuARm0MhaWM7Pn\n" +
                "b7yjOMn06D8K+rdEuo9W0G2kdCsrQAnPr/nH50sJJNNG1RHJ6/xJJdQthoEO9eu6Nj8p/A/zrh5p\n" +
                "5IYQAMiPc4XrkZyR9Mc16F4ljS3j8/YUVYmiJA5Cn5gD9Cp/OvL71mimlijODEDs7gjGcfr+Vb1F\n" +
                "qYIWa58yaJYzuTGAO+CMkf1rso9Y/sTw2s0o3BiqsCf4T8v5ZxXGaDYNcPFn7isOv8PoP1xUXivW\n" +
                "t6SWMLfIuYwOzAf1yK8+tX9mnK5jXnyxt1OP12QXGpPIrblbkZ6j2rGKc1bbJ681Cy5r5qc3OTke\n" +
                "XYqsvtTNtWivrUWKjmY7HUgetPVO9OUe1PArB1Q5Ru0U4CnBacF5rJ1Q5R0R2OGHBBzn0rptG1SQ\n" +
                "XAiLKIuC+T2HQf57k1zQWrFu3lsDk8HPBrSliHBlrR3R1urWKSbmhYBWyCR29T+lcyssVubh2x8w\n" +
                "3kH0XoP1H61saZqGWaKc5QqeP0A/M1FqOih5CYmIVuB7Z/ya92jiFNXR7FCsqis9xLMyXd1GRnEh\n" +
                "I/x/P9AK9K0gLIWfO3zHBbnt6ficflXn2mR/6XBBEMElsnsFxyf1Fd9pYEUCuJA0jyeZgdupH1r0\n" +
                "KcktToaua1yNtrFOr4/fiTnuAOn6V4l8Q9SEGgTSszedMHRTnk7mbB9uD+hr1zVbxYLaOJ2HbcAf\n" +
                "UYJ/nXz34+vJLo2luE4QEkA+5/xP50VpLoETmNBfZPGCCvOPmbAr23QJPJhieOJRxnftDZ+hI/pX\n" +
                "iWlRbJ1Il2H35x+Feg2F3KkIjjlMynAIyVOPoaypSaRMz1+01aFwP3wXHJ3DH5muf1HxVBPqi2cb\n" +
                "PdTSZWGFULtI3sARge5/OsGKSa6hwD8vQrnkV5n4/u/E/hSWe+8OyLaT3EflvchQZI17geg7/hXT\n" +
                "Gbk7MhI6/wAWapb6VEkvirxLBo8XLfZodjzYI6A9+noe3WvAfEfxK0WWV4dEt7+8Y4QTSv1Pb8a8\n" +
                "31PU9UnuLpdTvp57idsztLIW83ByCSevtWSLyeK2lszPKttJIsrwhyEdlBCsR3IDNg+5rtjRT+Ip\n" +
                "SfQ9D8O+MGk1doJN0RYYAk55HOPbmu/0jVopJVSNlk28BV5yff25rxOzggm8ORyyAJN57+Ww69B/\n" +
                "WvQfBOn3jFJoSJUddyiTPBz09q5K9KMX7ptHVXZ7zYxO+1nKtubHyCuj02yk8xCVYJGAAG9z/wDW\n" +
                "qPw9pklrYQLLCGuNoYkc++PrXZWWlNcsf3ZOSG467ex/WohTbZnKSRWhswgIUbdp4HboOlNltAis\n" +
                "FXDbiCDwMGunTSiGjbaSUwT6+hFWZtKV4QdvzknGO2M//WrvVJmFzzG5tHfavlgsx+Ykeo/+tWDr\n" +
                "Gj/b4ZYIEImkH7r0bv8Ah6Zr2F9BKRmfYPMbjB6fUj25NcxqelXTziJEmUn5QVGCF69exPWsXRa1\n" +
                "LUz4v8c6HdW9/PJfO2VbDKfu+/6156bu4OlpYC5dbdJTKId52ByApbb64AGfavsX4oeAzd6at/YW\n" +
                "zyl49sirhzuHPQgZ56/U14BN8PGlLTNbuzj/AFkAyCPoP8P1rWlLl91lN8yuebWjtBcCXhsc4z1r\n" +
                "0z4c6BfXOu200cklvJnezK207c8Djnnk4+lW9G+HyNMot7dGkyDuOXxnpwen417l4R8N2eiWqSw4\n" +
                "MxB3u5IYkn+f4Vc5pohux0FxEYYtsRYkDLZyf/11w3icTS27GSFwp7+n4V6JMGeHILDHIx83+A/G\n" +
                "uE8R2pmicxyvsTqHIyf51xVF7rIi9TjfDZax1xJos8MAyf3hnkV9Q/DzWHS1SOZh+6G1v7pU/dI+\n" +
                "uMfhXzv4csD/AGmmxDu3AgdTXr8aHw34iW1AItr6LKZ4Khhnbn2f9Kxw14+8azmm7HsHiOzWbS5H\n" +
                "CB/l6njI6814fqNpMdSkQKfl2lcnOVIGPy5H417npl5HqmgRuzZJUkj0z2I+teY6/EulTvK20SRs\n" +
                "0ceR1/iGfpyK7cRJKHMZSahqzAv510fSDBG/79wGbHUEEciuGvZTPdSyE8O27Hpmr2pXsl7cM5Jw\n" +
                "e3p3rOI59a+RxOI9pKy2PKqSc5XZWZaiKnmrJU5phXrXKmRYqsvFRlDnpVpl9qiKnPSi4HVbRShR\n" +
                "mnhSTxUqx8e9c1jTlIQtPCmpxFnrTxGBWTVhcpAqn0p6qPpUwSlCVmOwkLeW6tnO0hvy6Vv6ZeKY\n" +
                "THcPuMp5J6KMjP8AX86wxH7VKm5cEcEVtRrSpu5UW4u6N+C2MN7ESPlc7lA9zx/OuyWa3igALbVx\n" +
                "tA6Yz8ufyOK47T7gToIHJMgwqHOegA/xq5q106WjR7iNq8npzx/U19LQrqULo9mlNVFco67qrXP2\n" +
                "iQH7wbYo9fm4ry/W4GuLgtycDGa7iGG4u7OO6kXEajIb+8x5P86wtQs+SVTJ9cV0t33G3Z6HKW0S\n" +
                "xMPl2gd84/Wuv0NH2mRIspjruzXPSCSNvljJxznFb/h+N5rdgzBAT0DY/WnTepM3odTblnJwq7cf\n" +
                "dxyKj1aytNU08214u9QMHewIPtgA/lVqBHihAcvg8Ahwfwziq107K4aNQoAx8wH+NbNuLuRHU+fv\n" +
                "FnwwAdpbdI5rYsRH5iMjL/s9M/0rhR8NWaTH2aRcjuxYA/kM19WXMCykzTuUKg/KGwOnX1/AfnWB\n" +
                "c6S884jFokSOSqCU5LnHJx6AV1QrOxaPBdK+HLhVmjleRkPTBxz0xmvf/hz4HSxs4WmUgxDccrk5\n" +
                "POK3NB8OwPdI3lARocL6HAwTXpOmW0EQW3jQOq8nacD8al+9Lc05tLFfSvD1xcXLXh47jAx37j/C\n" +
                "u4tNPEKqGwy9QccinWXzKAVZAewHT8a0o4gOpJGc8+telSpKCujmbbKhtY/NByA2COO9K1vGFHQM\n" +
                "MfnV/wAoA5pTEpbJ5PWt7isUUjj8vJAI/nVeawSTdiFeRgfStUoqjFIAoBGcUxNHH6nosUlo8UsO\n" +
                "B1DA8ZryHxJ4NWG7kmjiL5APy+g6/wCe4r6Dnti6kg5J7k1zWo2MZjaC4iUoejHoD/SuepTW6CLa\n" +
                "PBG0EQTLcK0Ugb5TlCD+B7/StpLNsK8JDJj5ycYP14z+ddLqWjxxM8lvIu9MCSEuQWX3GDyOoIrM\n" +
                "zKzKnmSEDttOcema5GtR3K7xAwkAqB3fJUf4muQ1y3jeN1IOQO68/nXazQusZDxlyOhIxj6e9cfq\n" +
                "Ak+0lXZic8bm6VM1pYyT1G+DbMf2lGWj8whhgDpivVvHunx3Ohw3KNDBJAA8TM+09MFce/H5Vyvg\n" +
                "qyibUYvM6Eg5967Xx/a3K6UrxZwMALxx781hNOnQlJdCKs2tUUfAHiESZtpzjC5wTgjgZ/XP5iqv\n" +
                "xUiKXlmUdR5qlgM/eI4P6EfrXJaBqUFjqHm3WUJ6OoxntgitHxX4hstcjgi2S7IeEdWGQfcEf1Ha\n" +
                "vOqYuFXDtX1Jq1Yzh5nCuPWomGKvPbbyTC4lA/hPyv8Akev4Zqo618800cdiDHHNNOPSpT1pjDFN\n" +
                "DSIH5qLFTOKZinYrlOwVMCp1jGKVR7VKB2rJo1sNCDpQU9BUoX2p4XtWbQcpEIqesYxUoSnhSO1Z\n" +
                "tDsQ+X6igIPSpjz1pAuTSsKxd0qNmuEUTeWM/wB7FeiQeD7TVf391KssTjBCH/PQVyHh7QJdSvYw\n" +
                "6nyT/GvJ/KvX9O0yDTLbyoS545LEnNfT5Nh5TTlOPum9Lmvoef8AiPSktNsEEAhhRdsaqMACuAu7\n" +
                "Mgtuzj2r1nxMd7kbWBx3A5rzq7h+djjn6Zr0a8UptI6Uzh7uy3sThz7Ef4Vf0+1S3tkVXAc85wWO\n" +
                "e/QGtO4to3JBcADrgYP50nkwKFTcQOgVck/pWMdGNu6Ltk7RDBJJPZV5/P8A/VVqWKWVeiYYcrIB\n" +
                "VWCfbJs/dseg9f1/z71txKXjGSY2/uk9fx71slclaHOvZkMuZWjbsIQM/wAqVdPnmvhkOrBf9ZJw\n" +
                "Qvb6D+ZrfGnvMSwiUFeDIefyPUn8qheCGwgaOHDM5z5e7JJ9z2p8tjRMvWEEEMAt7c5O3DFew9PY\n" +
                "V1mkWqx2yySDOfmK7cfnXMaYrRRtLM4Zyc4AwuB/SuuilZrcIeARwp4P411UUrhJ6G7byIkfUH6d\n" +
                "KHvNvABAPTnrXPCW/TzP3TjaMKc8Ma5y68Wy6Us730LDYMnHIFd7nbcxuekfbMD5jiq8ur20H+tn\n" +
                "VfcmvnDX/i5qMtyY7CRQgOPTFc9P4y1y9HnGRm3Yzg0OT3RafQ+rV1mzkxtuFIPfNTrOm3eH3fjX\n" +
                "yjYeKtbVtomPBzgmur0fx5qVtcfZ79ikcrYMh5Gf6Vak+pDufQKX0MgO1gwHYGoL1EnhZSuAenFc\n" +
                "1o2qafHDDtkVi44bHWuqEttNGCp3Y9KtWasyLnnmqupmNtMQJUO0S7NwHoGHXB9RWSiEMd0boT90\n" +
                "owZDn88fjXT+MdKDSxX8YI28MVPb1rCEsyxAH7i9920/mDiuKSs7DZl3sUqwPm3lA/vk7c/Ug1xk\n" +
                "1qkl2NhA7nOCD+Nd1JIJQRIGZTkc4yv61jWtgWvzjBAPQ8fyqZIwvqdH4J05hcIyryvIIPy4q34+\n" +
                "u9XMXkJCVhH3mYDkeo/wrpdH0oW2nbrZMORngjI/GuN8Vx6oAz3AO3JyA5b8+K4se3Chyq+pE22j\n" +
                "zllBJ4HNQMuDVyZGV/mGM81XYV8fYwkiu6jb9Krvk8k5NWnHFVnIHaq6E3IWAzxUT9alJyaifk4p\n" +
                "ItbkTetR4ParG3jmmkVaRokdipNSr61XB5qeM5FOUTVomU1KuKiGMjmpFxgZrBoLDxUhPFMFKTnv\n" +
                "WVhNCYq7pljJfX8dvGpZmOMCo7OzubydYoIjIzHGBXrfhbwxDpcK3M8f+ksO/wDDXdgsDPFTstur\n" +
                "HGLk7I1tE0iHSbBIlUeYRljWqelFFfoFOnGlBQhokdqSSsjl/EMReMnaF75HevNNRWRGYqQv4DNe\n" +
                "raxyhAI/KvPNTtwWYg4BrycSve0BHMFwY2V2698iqTW8zNlm2J0C8DP+fen3ieW5b72Oc4zmo47p\n" +
                "3QrKpPGMuf61xiZdtEihkB8jMg6PJk/45roIJY3H+s/eY6K+APwwa521nD5jkm3ewer1vMIG8p1A\n" +
                "U8jGcCt4Mi5r3E0w5kuUPGAqjoPrUCsplGIwwx1C5z7dTSRossocAsT3JPFbtjFDCNzLubvuHeqs\n" +
                "2WmGl2svmmS5YZzwB2rdjuEjlIADbO5HWs+OXdIwaPP90DisbU9Wu7eXyUtyyqMlscV003ZDkzW1\n" +
                "/VykYAQ49BxxXgnxB8ZCZ4dHt3ZZ532MO4H1r1E6gLm082dmQ84z6elfPfxAlSx8VW2oSp8m5hvA\n" +
                "45rd3erIik2rlfXNQ0rRkWBFVmRfmcnnNcpL47giXy4pgFHYGvOvFd5e3GpTXPnN5LNgfNXKl2Jy\n" +
                "WP51vTblG6N5KzPck+IlvHhjIGOPXFdj4R+IWnapI+lXkqtFONnzdVbsRXy5uJ6mt3w3NaW+pRzT\n" +
                "ztGyMCAB1pzvGLYRipSPr/wT4huzqz6RfOXFu5RCx+8Ox/KvoPSruFLZTuzkc18p+ALyPVL6fUp3\n" +
                "WNOMEnHSvcdE1Njc+VNExts5Vx3FENNTknJJ6Hpl3FHeWQUcofQ4OK4u705raQqHfI6c8EfT/Irs\n" +
                "LB7R7MLD90+lZVzGxYhZFIDe2f0oqx1uLm0OPnQx7/MiCN/ezj9elW9DsczrPMzMT91MYOKn1W2Z\n" +
                "Jo5EB4AO5VPH1wKs2AiijLsMHphJBz9A2P51zyfK9TA6qKYRQhWk2D/ZGTXn3i19r7PtF09u3IZC\n" +
                "MfiOK17q6XyniGpIvtIxicfXjH61xeoJeyM0kkbvF2dDvX8xkV4OYYpzjypCepz8qQkkidv+Bx4/\n" +
                "kTVaSJlXeCHX1U5/P0q3MgOSpqpJkV4aRhIqsaqSGrU3Aqo5wMVbRBFnvSAd6aT6VKgyKzsaRGkc\n" +
                "VEc5qyynAqIpzVpM6EjqM1LG1VA/NSo1bNG7RdDD8akBqoGxUyP71jKJm0WFPNTRxtI4RFJJNVUb\n" +
                "muh8PWv23UYokcByfSs40+aSiiWegeDvDotLZL66UGZh8oI+7Xa1XsoDb2kcTNuKjBOMVYr9Aw1C\n" +
                "NCmoRR1wjyoKhlk2ipWYAc1RuZlAOWA9ia1qSSW5Zkas++M/Ma4y7BLMvbHc11eoTxIpZ/yJwBXH\n" +
                "ajewNwrgYP8ADxn+teRVabHYwdRDIWWFVBPfbkn/AArmLu2kDb3Yr7kcmuruJkPDOwY9EC5z+XP5\n" +
                "1gX6DB37d3oAf1rlkSZlvIFOWk+X+8RjH+NbtlcRyoCJhgdsf41yszqZcFy7noccCp7O/KTjb2PX\n" +
                "H+c/59KcZWIaPRLW4jhb7xjwOBkHNa0F7BKCFBLYJYj/AOtXHJctLZGYEjaOQPvfT2pvh/Xbe6v5\n" +
                "LQxJFMvTOefx9a6009CVc713leykW3UKx+67N1rlNQ1HULVJRHsl2cEjqa12uVMLxbyJGPygDgfj\n" +
                "XK6tcTR5j8rADY3A/erqQrlLUp5rmxMk9wIGT5toOK8d8czTX9pIkpUhchQOpFej6jqEcsT+Ydo+\n" +
                "7gj9M15T4leIzsYeoyGLdMe1at2QLVngOr+Yl88LAqFJwM8VnV0niBI5rp3jIyuckVzlbUmnHQ6H\n" +
                "5iVLAZPOUR5DE9qjxWrpUDFt5ToRyaqcuVXBHufgSfybKKNwSm0bxjkive9EuZJ4U2sfJGAF9q+d\n" +
                "/C2pSRxx4QcgLntivZ/DmoztMhZ/lxgBT0/CsoyVjlmj23w5NGuET5W7jOf51PLua+K4IDEk56AV\n" +
                "meHJD9mMwUyDHXHI9jV3VbieCwKw4WSc7UyMgj09q1m/duYmfIYNS1V47aVozHxnaCCPUMOo/UVZ\n" +
                "vobu1RUhQuijk43A/h1rR8P6A9vB57WckEh5+8Nv1A6imazFdRAvHAGYZyuT+YrzMVdU3JhynK3e\n" +
                "oWs0PlXIUjpuTPy/Tuv+eK5y4ie3YzW829O0kZwR7HHINauoXCXEu6ZCpHBcY3L9f7w/WsSZjCTl\n" +
                "uoyCp6j1FfJ1J8z1EyrPfTOCJ9k/oZFBP/fXX9az3khYH5GX6HP86ddOpckHnuRwD71nvL1qFuYS\n" +
                "GXDLuO0nHvWfK/OM1YkbJqsQGOSK1cbmYICcGrKr2qOIc4qyq4FZ8mptBDdvr0pCvPAqYJk04rit\n" +
                "FE6UWd3NSq4HFUgST3qZc/WtGbMuLJk1OjVTVX9KlVmGAayaIL9ujSyBV71614G0e3gjFy20yEcA\n" +
                "CvJ7CZRMoI5JFe2+ENMWGyW8d3Z3HG7tXpZZSUqydr2JSvJHVig8UVFLIEUktX1rdkdZDO+RjIH1\n" +
                "4rMuEZlJU7Qe9LcXTkkiUAY6VkzXMjvtLkfjmuGrNMSILq1hUnzJXc9wDhawr6ODkpGgwOGKdPxr\n" +
                "anjj5eVyAo6Yrn76J1UsjYJPGep+lcclboO5zWpQOJFkUFMY4AI/TvVS6kgMSrIytI3YH+dW7+6M\n" +
                "SmNmCsOcnIrkpL8/a/NbOOxzjI/rXM30HqxLyGQFtkW1m44zgD/GqBR4FPl7vMPBc849hWhLfwyq\n" +
                "d5O/hQQOAcZrLuIWjJdeT13Pz5a/41OxLRraVftDP9nkJkVgQehC1n6vpjR6rFqMbO8sZzGobCj6\n" +
                "1kNqConloSE7AdWPqfb2rYsNUQqLe4bJx949uK0hNbEbHbaXrq6hZAzOIp0GGiQ559hVPU1e4LSF\n" +
                "h8ozyelcq6tp9xJqWntufBLHOd1Tw6yuo27REmGQDDqBj8q6o1lsyXHqjC1a6jVDb7Q3zZJHIrzb\n" +
                "WWlnWZ5E2JyMZ6iu01uxvPLbyCAMnAJwRXCajY6mlo6lGlfq3YAfWqlWRUdDzm/syZ/KiAJ+83Gc\n" +
                "Vk3OlpGglkdYuec1t3lj4glk3ww7d7HAVeaoS+GNZlnKzBm7nnNb05pa81jS9zMsfsfmMJXCqDwW\n" +
                "71vRWIPzRzptJwApFV7fwfeOf3mQCflA6muo0v4fySKu6YkOcDB6fWirOL2Yr2L2hsYGWMu5yR8w\n" +
                "9fevX/DN4skkRYvvjOD7/wCNcnoHgQxCOOJnZxjJJ4NevaR4Uh0izFzfEI5AYJ0LCs4SlfyMJSTP\n" +
                "RfD13PPAltbnY6gE5OAw+vY101vB/at6oCqIYjgbuCD/AI/zrgNOv5JEjt7VDHAOMjqfb6V6b4cE\n" +
                "axAKvPc9a6o1Od2M1DudTBGqQKgOQOOtZOtW8At2Zl4/u+tbKHjAqrqUXm2rYQOwHAatK0OaDR0S\n" +
                "S5TxPWYUhuGaDLKedp+8tcvcTAqUzweR7Gun8UPNHO4ljMTBvl2jA/CuHvLneGJ+/wByO9fAVUoz\n" +
                "ZxNlaabkjNUXl561HNcEsSapmfJxms4SMpFtpM0i8mq6sTUyHFdsNRxiWE+9VpOTiqkZ+birCHBF\n" +
                "O2p0KJZxgUdabvGKbkHvTRoiaNRxVtFBAqsgyc1aUbRSZoS8CmHrRmrFrCsrgPwKztclm34U01r7\n" +
                "VYty/u1YZI6mvoG3jWKBERQoAxjFcL4D0aOG2+1GHAP3WPeu+6Cvqstoezpcz3Y6a3YMQB1qjOAw\n" +
                "JBH1qxI+AcCsu8uHGQign0FehNotsy7wRqSd569MVWyudyIvHJJIGKjuricEmWB+vqCKypbqeQFI\n" +
                "5R1ztVQDXmuSuynqWbmVI13BlB55B6Vyeq6q0e5QFJ6DaTnNadzI/m4ky4zzweBVC5toY2Z9qRt1\n" +
                "3uckD0xisJajVlucfczXEjN5kbHB4XG4k1j3GmXNwuWZ4Sxy20Zx+Y4rt2W1CZUDJ6E9frWHqFzB\n" +
                "CJG3Nk8Zao5Uhts59bJIb5Ai7goyc1HcTtOrRkcbcsqjHNaFpcRysygbcZ+YjGOMiqkyJATIF3ZY\n" +
                "M3P+elJx0JbOfnQQyGYqPMOCoP8ACPX8qpfaQqM5IeTpx0X/AD60s1y2oaldIhBjiChWXoe9RSID\n" +
                "GwI+VOMDvxWL8g9S3DrM8KNGpLp9aUa7AJ0ZYhkckjpn1rGmceUAv3CeeOTVcRlo8HJZh8351m3J\n" +
                "bFcqaO9i1TSdTXyy4jkHGW70y80gHICqVI/OuF5iuYgrYy2BV2LXr6CYqJWdArMM/n/hWinfczcH\n" +
                "0Ll5pLRkeVbAk8ZA6VkvoiwvllJJ6cVuL4odSsU0IZgASfU45qymq2twrH7MWYLuH9f6/lWsZ9id\n" +
                "V0Odg0fKtE0WAMMD+PNdPpHhfywZFyIiMnd296WPU4Qm+C2HPy/MOh6/4/lUi6he3BVTKQB0Xbit\n" +
                "4+ZDuzpoZ9N0mNohmS4IBD44XPT60n266uiPtT+aEXaC3Qjt/wDrqhawmaARFAGQfu29j/Cfx/nV\n" +
                "63hljIJXI7g9K2dwUUjc0y4G5fKcD2OP1FeqeGLzdEqSYz0ryq0tSzhokU+y9a7jRp/spXzVZB/f\n" +
                "B4FaUrxlqNnpySKGAB6dqztd1Caz093gj8x/Sobe6aUKySBx696TWHVtOfGN2Ohrqqy9xtMUnoeR\n" +
                "+I9Ua7dmuEjWT+4OTXnd9Ll2ONvtXZeIZow7qgjUg8lea4W6fc7EnPvXwmKu5XZzmdM5yarjJOak\n" +
                "mIbNQofmwa5qS1IaLcR7VYB4qmh5xVlTxmvUjsXFFmJsVJ5ozVLfg9aYZj61jOVmbGiZqaZ+eKzv\n" +
                "OJHWmmTms4zuNHYrFipdhxxTwVap0Uba1ZsVAhLVu6Hpk99exxwRhiSOp6VnRwPJIFXua9i8DeH4\n" +
                "bKzW8dd0zDqe30rqwuHdeoo9CX2Ov063+y2EUJABVQDipZWY8J+dPdto9TVWaVguF619iklGyHsr\n" +
                "ELyMnDSZ/CqM0jn/AOvT5xLgku34YArNlnWJSQGJ7BeRWEpWERXKCX5VJH0xWHcosLYados9CD1q\n" +
                "xPPPcNt2FQO7gY/Ssi6tJyTK16y7uyZx/SuGbvqkUhsmIVO4rhcnGMFj9RxWNPcR3G5YoSCTyOQP\n" +
                "zpb2O9RMb1kjIIzg7j9OtYzTONylzuHJHZMn0H8vWsGUl1CVpJpWACiMdWYfyNZ1zDGznaiswOM5\n" +
                "IP51bkuDwg+ZUGGyOT7VAYXeMtKyxJjPB6D0/wD1UWHcoyqII3A2kk7ieMcf/rrm9WuJzZjDH5/l\n" +
                "bsT6109wkYhZ1xgrgZ9MZ5rm74Rx23mSNyi9D7nilJaEpmNplvJBbFsZcsQ2fYYpJyPOWNQQpBY1\n" +
                "pQoqRPHkYLEn8f8A69YN6+xBNkl3JwPYdKzcbId7sjGzbAM9uT6HrxThgR7/ALoPP6ZqgZGDjHzt\n" +
                "jLY6ZqW0mN1G6NkYbAz0x2qNNi7EM7Fr+Fu2ePp60+O2Bn+cMVZSf05qSaFseaB0Ax+dPW4UIVkH\n" +
                "KjaPr0pKOuo76aCLDvJZiGJOQcd6swo0bK0bbWByMVnrPtnYxf6t8EqRn8jVxbjMhUDzUIzsIwfw\n" +
                "96tJEO5swoshK4G2QfLkfxDt/T8auwCMfe3IR+IPvWXZ3GDtxlcggkYIPY1tQxhXDbiityARnHt+\n" +
                "ByK6I6mbVjVsmWJlPmZH4CuosnSXhsHPBwOPrXLQ26leChH+7/nFaFvA8bhl3Jg9R0rojdEM7O1t\n" +
                "UDAl8Cuw05YsbXCspHQnrXB2FzcIAGIde5rrtLu4ZgEdQD6Gt4NXIdzq7S0ghfdGflPYdqzPFEpj\n" +
                "tmUecmR95D0rV0+NgRtBweornPGV3axTBWupbaVR96P5vzFPE2jSYm9Dy/X5be4jZBdh3HTeMMa4\n" +
                "mYdRkH6Gux1h7e4chZ4bg92AKt+tcrcRhWbAwK+Krq7IMxk5OKrldrZFXiMk1WlXmopQIYikdalV\n" +
                "+MVVzinLJgV3Wsioq5M7elQO3HWmvJmomkNebWlqbEoc5p341WVualD1NO4zuUlIbk1ehkzWRyDz\n" +
                "V62JZwg6mu7luWmdRoNqbzUY4xGWBPNe66fAttYxxqMYArgvAmgywot7MBg/dGK9FY7Ur6fLsP7O\n" +
                "HM+ok9bkU0mOnWqRkkLFiOB6dadKwdiN3BqvJIsanaeB2AzmvTYDJyrDLqWPYMeB+FUZ4QU5XJ9B\n" +
                "2qO5v3ztVWHtzn9OlZOo6u0Mewk+pxXPOUbagOvJ/IUiQFQP7vJrmbrUrRHLYl3D+Jc8fiBirD38\n" +
                "U6cy4JOAFHT3OKqS/YLdizO7Sjrub5c/41wzvLYtaFKW6nkb91FJIpGNwUfzrn9QnuHDKqsuOu71\n" +
                "HbNb0t3Gc/OM4/hHSsS+kuShkRYWUfx9SKyZSZVV3YRiRVDgE4Xnn6ioJ5iISCVDYHAJzznqaptd\n" +
                "SiUhIn3Add3Gf896qyTySQyOWxuPA3ZJOc59sgn86EJmi2dnls/QgMD7/wD1qybqETShXBYNyc9+\n" +
                "9D3Mibg6kqG8zP8Af4NOaaONTO53NITgj6cU9xbFB0C28hIALKQfbI/+vmsG5tzIpIAPGfpXQOQP\n" +
                "ll5GevqOhH61UNvhnjY5Kgr/AIGk43QXsc8LcCMnbwcYNVrZWhmlBGePmP6itueE+Y0PKqMYI70s\n" +
                "dnh1GAOOlZOGuhaegm2N43VvuuCQff0qg9tukZCoAOML7c1otGygA8KDkAdqSVA92rYGCoHHY/4V\n" +
                "bQloY0VpkPAxyVJXIqaC2cgoxyV4BI71fMZW93ADJ6jsalEIMhK844B70uUfMVoke3kVmUhTwcdK\n" +
                "6K2KywiNnGOq5/l/n0qlEoaMspGV4Ix972q0myCAOchQeoPIq4qxLdzYtVlhYIHyOynn8q3raXGB\n" +
                "IOnqOK5S3uHTbsTzIz1HcVs2t0GXh2jwcfPW0WZM6yAouGVGU5+7uyPwrbtEEmCoYN6EYNclbXEh\n" +
                "Kgv+I5BrqdMLkrliTng9a3ja5DPRNB3eWu8n8eornfiRbolis4s4pyeCzjkfQ5rotFdTGvXNZ3j8\n" +
                "TnQyYWHuMZrXFK+HkS/hPn2fb5hYIyexOcfjVOYAqc81p3ULFywJ5PIPas+VCM18Ik+YhszX+Umq\n" +
                "8mWqzKp3VXfg11QdiSm5K8UzPy0+Yioc8YrSexcAz3o6mmjJNTKK8+Ubs1GAUZqRgBUVbRVkM76V\n" +
                "OelXNIg8/UooweSwqhI5JwOtdr4D0Z7u/WeVDsU5GK9GhTdSaSM2z2fRYTb6ZDH2Cirkz/ISDUAY\n" +
                "QQqijAAxxUUsuIs5ya+yjGySLvZWI2J5LHA9BWNez7jhAT9TgVLPefMctgD2zWDc3IkkJLnHoBnN\n" +
                "Z1NrEqRLLMyDklPY1hXe+YsQwII7ggfXNTXkqnO2Vi3sAaxp7ecnPmS5P8O4L+dcM+xoipc2RVDJ\n" +
                "5qKzc8cf1rnLu4ubX7piCjplzkfjmtS7N6rER72B6nGQKzpCGUmTyVc92AJP4/4VyuN9jRMyf7cn\n" +
                "jf8AegSRHgEHI/8Ar1ettSlu28ueWFI1wQAc7h6jpWfd2yykqsgBPdAAD+lZ4W6sDv3iRc/cIBwa\n" +
                "mzRWjNy8jKZSPgycFsc9OAKypFkQssbEMvRf7pNTpcrdr5wd0VQcjpj246fjVecbVeMyEupBY565\n" +
                "H607E3KF2C0OQwPzgqcY3HNU1eeJhAQDCCAme3v/AJ96uswyq5yDycg5A7ECq13KkbLnOARx3pND\n" +
                "uPLLNbBXJyrHPqM9/wA8VDLcmN080FWx8rA5z2ocOqLNEcAdPx7f596rXXlPDHjnBypH8J/ukU2I\n" +
                "uxskxcHKsTnBHX6H6VYmRI4mBxgj5W9if8awzJLblZFOYyMFYxyp/pVpbpzGf3pmiIyc/Kye9KL7\n" +
                "g0Pu2YyqOg9umSP/AK1QqGOJMcjH5f5/lUkzM1u7K25FI5HOPb1pImDMSi7k+6wB/WnbUOg+VR5o\n" +
                "kAwo6/Q007Y5MnuQPxzwahlkdX2sc5yoI7j3polZoiB0yB78CgC8jqysOBv/AF7fhUsJDW7wODkn\n" +
                "Iz1NUIXkVtpAYt2HBIqxblzKxKkL1BPFUhMniEjFkSRlk5GCSOa0raGeQLudg+MZPX8agMasqyrj\n" +
                "fjOe9atkVbCtkN1FaKPcVy/p/wBqhba+W9zXb6S6uyb2wcCuYtXUkBiEYHjIrpbIowzuAYdcdK3h\n" +
                "HUylI9H0jKFcHj1qj48KPouz7SYpOoA71T0i5kjZcNkHsDxV3xZPbnSw09uZCwwcDJFdFeN6EkZ3\n" +
                "0seITKcnLKx9RWRdEKDzW7qF1AJX8mIoAf4uK5m9uwcjAH0r4eUOV2JKEshDcmoHkBFQ3NwB3qt9\n" +
                "oB71pYCSVsmos+lNL5NA705O5SHjrVlPuk1VWrSfdrJIpjH6VXJwamc8VWJ5qSj0NIWluFRVySa9\n" +
                "w8HWQstKjZ4wCRXlnhuCKfUYzIucHNezQnyrRAnAxX0+XUldzIe5enuvmCjrVa7uGEIHFVBKxl5x\n" +
                "VfUHby1Oa9xsVyhcyliev59ayJdxO1tgUnpnJNTTSsy4OO9ZiSuIyy4UknoK5pajQl3JFbj5VCsP\n" +
                "U8/pWPLfCQ42bVHq23/9dV9TnljBdXJZuDmsBrmWSBpGbLA9a4Zz10NlHQ17lC//AC1XpnkZA+ua\n" +
                "yWgMjs3mGUdyHBwKpJcyyzOr4KqwGMdakuLuWOWCFcBG6jn0pKzK2LM93DbxFEXLbeMrknH0/rXP\n" +
                "TXTrMXVpGQfeCrwD9PStO9cxRAjBzzyKwbuaUW7OsjLggbQeDUzGhJLxPO81AUYcfI3B+oqSHUI5\n" +
                "eSoCqfny2c/5/pWZcwLtklV2R0U8jvj1zWWV3SRTBijMm47TgE49OlZ3aKtfY6WeQpmYMDlfz9/1\n" +
                "qpeSiUMwGeNoz2pBnHl5woTI/E//AFqgHMkydtufyP8A9aqepJfDiPy1KhklHfpn/OahmjiKmZMg\n" +
                "jhxj5lHv6jpVdJnKrGcY3/llWP8ASld2V2A4GF/UdKdhNiK6RS7xtKt/rE/rVpUBTzIWDL6eYOPz\n" +
                "rMnYqyHggqeDyB/nNIABKhAxuAPBNQ3YpK5cEMqy+Yjkj+OLIwf1NRWwKSSQgbdjZUE9RnpVq3wJ\n" +
                "1TapBGckc1VI8uVXQ4w+MdRj0p2vZiv0Hys00YkU52nB3VIEVWxjG/rjsaLcD7RIpGVYqSD71KnE\n" +
                "23qA+OfTNVa4XGuojkicjoSMDtir6DDb0bgjOPSs25cmSIYGACP5/wCFS+Y6wghuVPFUiWzShmEg\n" +
                "VCRlc57VpW0qIcblwema5ieWSG4jKNjcuSKuiaT5TkdPQc1VxNHVLexAgMyEY4IJBH0rTsLi4dgY\n" +
                "pXA7bmrk7aRsA8V01n8rfLxirTM3oeg6BHNHKskhJJIzxzVj4hSTxabBLAz4GchRWboE8ocAMeTi\n" +
                "rnju/nt9KtdgQ7zglhntW9e31eRjc8ZvLosWJcsTzya5+6nJJrpdWJKl+7cmuNuid7V8dVjysZUu\n" +
                "JiT1qJH6VXuGbf1ojds1a2EaKHJqUHFQRHOKl71LLRKvWp0OBVZTU6HioKEfkZqA4zU7dKgI5qGO\n" +
                "5//Z\n";
    }
}
