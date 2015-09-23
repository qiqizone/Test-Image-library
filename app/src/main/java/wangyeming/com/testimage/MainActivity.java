package wangyeming.com.testimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPickerActivity;
import wangyeming.com.testimage.image.ImageHandleUtils;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE = 1;

    private SwitchCompat vTakePhotoSwitch;
    private SwitchCompat vMultiChooseSwitch;
    private EditText vImageCount;
    private ListView vPhoto;
    private FloatingActionButton vPickImage;

    private List<String> photoPaths = new ArrayList<>();

    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        vPickImage = (FloatingActionButton) findViewById(R.id.vPickImage);
        vTakePhotoSwitch = (SwitchCompat) findViewById(R.id.take_photo_switch);
        vMultiChooseSwitch = (SwitchCompat) findViewById(R.id.pick_patten_switch);
        vImageCount = (EditText) findViewById(R.id.image_count_input);
        vPhoto = (ListView) findViewById(R.id.photos);

        mAdapter = new ImageAdapter(this, photoPaths);
        vPhoto.setAdapter(mAdapter);

        vPickImage.setOnClickListener(this);

        vMultiChooseSwitch.setOnCheckedChangeListener(this);

        vPhoto.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.vPickImage) {
            boolean isMultiChoose = vMultiChooseSwitch.isChecked();
            boolean isTakePhoto = vTakePhotoSwitch.isChecked();
            if(isMultiChoose) {
                int imageCount;
                if(TextUtils.isEmpty(vImageCount.getText())) {
                    Toast.makeText(this, "默认选择3张", Toast.LENGTH_SHORT).show();
                    imageCount = 3;
                } else {
                    imageCount = Integer.parseInt(vImageCount.getText().toString());
                }
                Intent intent = ImageHandleUtils.pickMultiImage(this, imageCount, isTakePhoto);
                this.startActivityForResult(intent, REQUEST_CODE);

            } else {
                Intent intent = ImageHandleUtils.pickSingleImage(this, isTakePhoto);
                this.startActivityForResult(intent, REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                photoPaths.clear();
                photoPaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS));
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        vImageCount.setEnabled(isChecked ? true : false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = ImageHandleUtils.previewImage(this, (ArrayList<String>) photoPaths, position);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
