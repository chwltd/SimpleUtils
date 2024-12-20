package com.chwltd.simpleutils;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.chwltd.simpleutils.databinding.ActivityCustomImageBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomImageActivity extends AppCompatActivity {

    private ActivityCustomImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCustomImageBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        String img1 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.6528TwmrMjANOqPqFNjDlwHaEK?w=278&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        String img2 = "https://tse1-mm.cn.bing.net/th/id/OIP-C.Nxb8Lklm2XdfWIxv1I6aYgHaEc?w=262&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        String img3 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.bde3F6HHsXLBc4jEEX18KAHaEO?w=276&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        String img4 = "https://tse2-mm.cn.bing.net/th/id/OIP-C.bPIywmUndauv33vQSoUSugHaEK?w=308&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        String img5 = "https://tse4-mm.cn.bing.net/th/id/OIP-C.DDPh0iH6FGp1UNhz6mYf5QHaEK?w=308&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        String img6 = "https://tse2-mm.cn.bing.net/th/id/OIP-C.Gu_Xe6UuJYsG35Z3tOFLNQHaEK?w=279&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        String img7 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.gKGUOUBdHVlfVl7Ms4vK0QHaEK?w=329&h=185&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        binding.customImageView.setImageUrl(Arrays.asList(img1,img2,img3,img4,img5,img6,img7));
        //binding.customImageView.setImageUrl("[\""+img1+"\",\""+img2+"\",\""+img3+"\",\""+img4+"\",\""+img5+"\",\""+img6+"\",\""+img7+"\"]");
    }
}