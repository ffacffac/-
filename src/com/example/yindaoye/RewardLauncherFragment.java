package com.example.yindaoye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * ����ҳ��
 * 
 * @author ansen
 * @create time 2015-08-07
 */
public class RewardLauncherFragment extends LauncherBaseFragment
{
    private ImageView ivReward;
    private ImageView ivGold;

    private Bitmap goldBitmap;
    private boolean started;// �Ƿ�������(ViewPage����ʱ������������ֵ)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View rooView = inflater.inflate(R.layout.fragment_reward_launcher, null);
	ivGold = (ImageView) rooView.findViewById(R.id.iv_gold);
	ivReward = (ImageView) rooView.findViewById(R.id.iv_reward);

	// ��ȡӲ�ҵĸ߶�
	goldBitmap = BitmapFactory.decodeResource(getActivity().getResources(),
		R.drawable.icon_gold);
	startAnimation();
	return rooView;
    }

    public void startAnimation()
    {
	started = true;

	// �����ƶ����� Ӳ�ҵĸ߶�*2+80
	TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0,
		goldBitmap.getHeight() * 2 + 80);
	translateAnimation.setDuration(500);
	translateAnimation.setFillAfter(true);

	ivGold.startAnimation(translateAnimation);
	translateAnimation.setAnimationListener(new AnimationListener()
	{
	    @Override
	    public void onAnimationStart(Animation animation)
	    {}

	    @Override
	    public void onAnimationEnd(Animation animation)
	    {
		if (started)
		{
		    ivReward.setVisibility(View.VISIBLE);
		    // Ӳ���ƶ����������������Ŷ���
		    Animation anim = AnimationUtils.loadAnimation(getActivity(),
			    R.anim.reward_launcher);
		    ivReward.startAnimation(anim);
		    anim.setAnimationListener(new AnimationListener()
		    {
			@Override
			public void onAnimationStart(Animation animation)
			{}

			@Override
			public void onAnimationRepeat(Animation animation)
			{}

			@Override
			public void onAnimationEnd(Animation animation)
			{
			    // ���Ŷ������� �����ı�͸���ȶ���
			    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			    alphaAnimation.setDuration(1000);
			    ivReward.startAnimation(alphaAnimation);
			    alphaAnimation.setAnimationListener(new AnimationListener()
			    {
				@Override
				public void onAnimationStart(Animation animation)
				{}

				@Override
				public void onAnimationRepeat(Animation animation)
				{}

				@Override
				public void onAnimationEnd(Animation animation)
				{
				    // ͸���ȶ�����������ͼƬ
				    ivReward.setVisibility(View.GONE);
				}
			    });
			}
		    });
		}
	    }

	    @Override
	    public void onAnimationRepeat(Animation animation)
	    {}
	});
    }

    @Override
    public void stopAnimation()
    {
	started = false;// ��������ʱ��ʾ������Ϊfalse
	ivGold.clearAnimation();// ���view�ϵĶ���
    }
}
