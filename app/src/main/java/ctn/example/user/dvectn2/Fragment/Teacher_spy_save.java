//package ctn.example.user.dvectn2.Fragment;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.kbeanie.multipicker.api.ImagePicker;
//import com.kbeanie.multipicker.api.Picker;
//import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
//import com.kbeanie.multipicker.api.entity.ChosenImage;
//
//import java.io.File;
//import java.util.List;
//
//import ctn.example.user.dvectn2.R;
//
//import static android.app.Activity.RESULT_OK;
//
///**
// * Created by User on 8/3/2561.
// */
//
//public class Teacher_spy_save extends Fragment implements View.OnClickListener  {
//
//    EditText se_ins1;
//    String frg_sa1;
//    ImagePicker imagePicker;
//    ImageView imageView;
//    Context context;
//
//    public static final String TAG_STUSA1 = "STUSAVE";
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view1 = inflater.inflate(R.layout.teacher_spy_save, container, false);
//
//        init(view1);
//        return  view1;
//    }
//
//
//    public void replaceFragment(Fragment fragment, Bundle bundle) {
//
//        if (bundle != null)
//            fragment.setArguments(bundle);
//
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction frgTran = fragmentManager.beginTransaction();
//        frgTran.replace(R.id.content,fragment).addToBackStack(null).commit();
//
//
//    }
//    private void init(View view){
//
//        view.findViewById(R.id.btn_se3).setOnClickListener(this);
//        view.findViewById(R.id.btn_up3).setOnClickListener(this);
//
//        imageView = view.findViewById(R.id.img_3);
//
//        context = getContext();
//
//        imagePicker = new ImagePicker(this);
//
//        imagePicker.allowMultiple(); //เลือกหลายภาพ
//
//        imagePicker.setImagePickerCallback(new ImagePickerCallback() {
//
//            @Override
//            public void onImagesChosen(List<ChosenImage> list) {
//                // Do somethig
//                // get path and create file.
//                String path = list.get(0).getOriginalPath();
//                File file = new File(path);
//
//                // convert file to bitmap and set to imageView.
//                if(file.exists()){
//                    Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                    imageView.setImageBitmap(myBitmap);
//                }
//            }
//
//            @Override
//            public void onError(String message) {
//                // Do error handling
//            }
//        }
//        );
//    }
//    private  void onSelect(){
//
//        imagePicker.pickImage();
//    }
//    private void onUpload(){
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK){
//            if (requestCode == Picker.PICK_IMAGE_DEVICE){
//                imagePicker.submit(data);
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_se3:
//                onSelect();
//                break;
//            case R.id.btn_up3:
//                onSelect();
//                break;
//        }
//
//    }
//}
