interface DataStore {

  // Adds item to the datastore
  public void add(Double item); 

  // Get moving average for the last n items added to the store
  // If there are fewer elements in the store than the specified n, returns null
  public Double getMovingAverage(); 

  // Allows direct access to an element
  // Throws IndexOutOfBoundsException if out of range
  public Double getIndex(Integer index);

  // Convenience method to get last item added to the datastore
  public Double getLast();

  // Convenience method to get the size of the datastore
  public Integer getSize();

  // Fetches the n value specified during initialization or by setMovingAverageSize()
  public Integer getMovingAverageSize(); 

  // Allows for updating the number of elements to include in the moving average
  // On update, the moving average will be recomputed
  public void setMovingAverageSize(int n); 
}