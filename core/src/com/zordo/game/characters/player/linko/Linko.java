package com.zordo.game.characters.player.linko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class Linko {
	Rectangle linkoCollider;
	Vector3 position;
	Boolean flippedRight;
	Boolean jumping;
	Boolean playerControlled;
	Boolean stepping;

	public ArrayList<Heart> hearts;
	public int health;

	Animation<Sprite> runningRightAnimation;
	Animation<Sprite> runningLeftAnimation;
	Animation<Sprite> walkingRightAnimation;
	Animation<Sprite> walkingLeftAnimation;
	Animation<Sprite> animation;
	
	Sprite[] runningRightFrames;
	Sprite[] runningLeftFrames;
	Sprite[] walkingRightFrames;
	Sprite[] walkingLeftFrames;
	Sprite standRight;
	Sprite standLeft;
	Sprite jumpRight;
	Sprite jumpLeft;
	
	int jumps = 0;
	float jump2 = 0;
	
	float elapsed;

	// accessing sprite assets from the directory files
	String standuri = "link-standing-sprites/";
	String runuri = "link-running-sprites/";
	String jumpuri = "link-jumping-sprites/";

		
	public Linko(Boolean playerControlled) {
		this.playerControlled = playerControlled;
		position = new Vector3();
		linkoCollider = new Rectangle();
		linkoCollider.x = 10;
		linkoCollider.y = 10;
		linkoCollider.height = 53;
		linkoCollider.width = 23;
		position.x = linkoCollider.x;
		position.y = linkoCollider.y;

		health = 8;
		
		// orientation and jumping status
		flippedRight = true;
		jumping = false;
		stepping = false;
		
		// animation objects
		runningRightFrames = new Sprite[6];
		runningLeftFrames = new Sprite[6];
		walkingRightFrames = new Sprite[4];
		walkingLeftFrames = new Sprite[4];
		hearts = new ArrayList<Heart>(8);
		
		// standing and jumping sprite creation
		standRight = new Sprite(new Texture(standuri + "link-standing-0.png"));
		standLeft = new Sprite(standRight);
		jumpRight = new Sprite(new Texture(jumpuri + "link-jumping-0.png"));
		jumpLeft = new Sprite(jumpRight);
		standLeft.flip(true, false);
		jumpLeft.flip(true, false);
		
		// running animation construction
		int j = 0;
		for(int i = 0; i < 6; i++) {
			runningRightFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			runningLeftFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			if( i != 2 && i != 5) {
				walkingRightFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
				walkingLeftFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
				walkingLeftFrames[j].flip(true, false);
				j++;
			}
			runningLeftFrames[i].flip(true, false);
		}
		
		for(int i = 0; i < health; i++) {
			hearts.add(new Heart());
		}

		// setting the animations
		runningRightAnimation = new Animation<Sprite>(1f/6f, runningRightFrames);
		runningLeftAnimation = new Animation<Sprite>(1f/6f, runningLeftFrames);
		walkingRightAnimation = new Animation<Sprite>(1f/3f, walkingRightFrames);
		walkingLeftAnimation = new Animation<Sprite>(1f/3f, walkingLeftFrames);
		
		animation = walkingRightAnimation;
	}
	
	public void move(SpriteBatch batch) {
		elapsed += Gdx.graphics.getDeltaTime();
		stepping = true;
		position.x = this.getLinkoCollider().x;
		position.y = this.getLinkoCollider().y;

		if(playerControlled) {
			healthRender(batch);
			if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				stepping = false;
				if(this.getFlippedRight() && !this.getJumping()) {
					this.setStandRight(this.getStandRight());
					batch.draw(this.getStandRight(), this.getLinkoCollider().x, this.getLinkoCollider().y);
				} else if (!this.getJumping()) {
					batch.draw(this.getStandLeft(), this.getLinkoCollider().x, this.getLinkoCollider().y);
				}
			} 
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if(!this.getFlippedRight()) {
					this.setFlippedRight(true);;
				}
				if(!this.getJumping()){
					animation = this.getWalkingRightAnimation();
				}
				if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					this.getLinkoCollider().x += 100 * Gdx.graphics.getDeltaTime();
				} else {
					animation = this.getRunningRightAnimation();
					this.getLinkoCollider().x += 150 * Gdx.graphics.getDeltaTime();
				}
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if(this.getFlippedRight()) {
					this.setFlippedRight(false);
				}
				if(!this.getJumping()){
					animation = this.getWalkingLeftAnimation();
				}
				if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					this.getLinkoCollider().x -= 100 * Gdx.graphics.getDeltaTime();
				} else {
					animation = this.getRunningLeftAnimation();
					this.getLinkoCollider().x -= 150 * Gdx.graphics.getDeltaTime();
				}
			} 
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumps < 2) {
				this.setJumping(true);
				if(jumps == 0) {
					this.getLinkoCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
					if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
						this.getLinkoCollider().y += 50;
					}
					jump2 = this.getLinkoCollider().y * 1.5f;
				} else {
					this.getLinkoCollider().y = jump2;
				}
				jumps++;
			} 
			
			if(this.getJumping()) {
				stepping = false;
				jumpRender(batch);
			}
			
			if(this.getLinkoCollider().y > 11) {
				this.getLinkoCollider().y -= 120 * Gdx.graphics.getDeltaTime();
			} else if (this.getLinkoCollider().y < 11) {
				jumps = 0;
				this.setJumping(false);
			}
		} else {
			healthRender(batch, (int) getLinkoCollider().x - (int)getLinkoCollider().width, (int) getLinkoCollider().y + (int) getLinkoCollider().height + 10);
			if(!this.getJumping()){
				animation = this.getRunningLeftAnimation();
			}
				this.getLinkoCollider().x -= 100 * Gdx.graphics.getDeltaTime();
		}
		if(stepping) {
			batch.draw(animation.getKeyFrame(elapsed,true), this.getLinkoCollider().x,this.getLinkoCollider().y);
		}
	}
	
	private void jumpRender(SpriteBatch batch) {
		if(this.getFlippedRight()) {
			batch.draw(this.getJumpRight(), this.getLinkoCollider().x, this.getLinkoCollider().y);
		} else {
			batch.draw(this.getJumpLeft(), this.getLinkoCollider().x, this.getLinkoCollider().y);
		}
	}
	
	public void damage() {
		if(!playerControlled) {
			topHeart().damageHeart();
			if(topHeart().getHeartHealth() <= 0) {
				if(!this.hearts.isEmpty()) {
					this.hearts.remove(topHeart());
				} 
			}
		} else {
			topHeart(health-1).damageHeart();
			if(topHeart(health-1).getHeartHealth() <= 0) {
				health--;
			}
		}
	}
	
	private Heart topHeart(int health) {
		if(health > -1) {
			return this.hearts.get(health);
		} else {
			return this.hearts.get(0);
		}
	}
	
	private Heart topHeart() {
		// TODO Auto-generated method stub
		return this.hearts.get(topHeartIndex());

	}
	
	private int topHeartIndex() {
		// TODO Auto-generated method stub
		if(!this.hearts.isEmpty()) {
			return this.hearts.size() - 1;
		} else {
			return 0;
		}
	}

	private void healthRender(SpriteBatch batch) {
		for( int i = 0; i < this.hearts.size(); i++) {
			batch.draw(this.hearts.get(i).getHeartState(), 200 + i*10, 200);
		}
	}
	
	private void healthRender(SpriteBatch batch, int x, int y) {
		if(!this.hearts.isEmpty()) {
			for( int i = 0; i < this.hearts.size(); i++) {
				batch.draw(this.hearts.get(i).getHeartState(),x + i * 10,y);
			}
		}
	}
	
	/**  Required: Batch, and Platform checked for collision
	 *
	 */
	public void collisionWithPlatform() {
		//collide from right side
		
		//collide from left side
		
		//collide from bottom
		
		//collide from top
		
	};
		
	public void setLinkoCollider(Rectangle linkoCollider) {
		this.linkoCollider = linkoCollider;
	}
	
	public void setLinkoCollider(float x, float y) {
		this.linkoCollider.x = x;
		this.linkoCollider.y = y;
	}
	
	public Rectangle getLinkoCollider() {
		return this.linkoCollider;
	}
	
	public void setFlippedRight(Boolean flip) {
		this.flippedRight = flip;
	};
	
	public Boolean getFlippedRight() {
		return this.flippedRight;
	};
	
	public void setJumping(Boolean jumping) {
		this.jumping = jumping;
	};
	
	public Boolean getJumping() {
		return this.jumping;
	};
	
	public void setWalkingRightAnimation(Animation<Sprite> walkingRightAnimation) {
		this.walkingRightAnimation = walkingRightAnimation;
	}
	
	public Animation<Sprite> getWalkingRightAnimation () {
		return this.walkingRightAnimation;
	}
	
	public void setWalkingLeftAnimation(Animation<Sprite> walkingLeftAnimation) {
		this.walkingLeftAnimation = walkingLeftAnimation;
	}
	
	public Animation<Sprite> getWalkingLeftAnimation () {
		return this.walkingLeftAnimation;
	}
	
	public void setRunningRightAnimation(Animation<Sprite> runningRightAnimation) {
		this.runningRightAnimation = runningRightAnimation;
	};
	
	public Animation<Sprite> getRunningRightAnimation() {
		return this.runningRightAnimation;
	};
	
	public void setRunningLeftAnimation(Animation<Sprite> runningLeftAnimation) {
		this.runningLeftAnimation = runningLeftAnimation;
	};
	
	public Animation<Sprite> getRunningLeftAnimation() {
		return this.runningLeftAnimation;
	};
	
	public void setRunningRightFrames(Sprite[] runningRightFrames) {
		this.runningRightFrames = runningRightFrames;
	};
	
	public Sprite[] getRunningRightFrames() {
		return this.runningRightFrames;
	};
	
	public void setRunningLeftFrames(Sprite[] runningLeftFrames) {
		this.runningLeftFrames = runningLeftFrames;
	};
	
	public Sprite[] getRunningLeftFrames() {
		return this.runningLeftFrames;
	};
	
	public void setStandRight(Sprite standRight) {
		this.standRight = standRight;
	};
	
	public Sprite getStandRight() {
		return this.standRight;
	};
	
	public void setStandLeft(Sprite standLeft) {
		this.standLeft = standLeft;
	};
	
	public Sprite getStandLeft() {
		return this.standLeft;
	};
	
	public void setJumpRight(Sprite jumpRight) {
		this.jumpRight = jumpRight;
	};
	
	public Sprite getJumpRight() {
		return this.jumpRight;
	};
	
	public void setJumpLeft(Sprite jumpLeft) {
		this.jumpLeft = jumpLeft;
	};
	
	public Sprite getJumpLeft() {
		return this.jumpLeft;
	}

	public Vector3 getPosition() {
		return this.position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}
}
