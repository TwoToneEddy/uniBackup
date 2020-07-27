public class Item{

	int itemNumber;
	Item leftChild, rightChild;
	
	public Item(int itemNumber){
		this.itemNumber = itemNumber;
		leftChild = null;
		rightChild = null;
	
	}

	public void recordItem(int itemNumber,String description,double unitPrice,int sort){
		if(this.itemNumber > itemNumber){
			if(rightChild == null){
				item = new Item(itemNumber,description,unitPrice,sort);
			}else{
				rightChild.recordItem(itemNumber,description,unitPrice,sort);
			}
		}else{
			if(leftChild == null){
				item = new Item(itemNumber,description,unitPrice,sort);
			}else{
				leftChild.recordItem(itemNumber,description,unitPrice,sort);
			}
		}
		
	}
	
	public String getDescription(int itemNumber){
	
	}
	public Double getUnitPrice(int itemNumber){
	}
	 



}