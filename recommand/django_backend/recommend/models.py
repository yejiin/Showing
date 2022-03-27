from django.db import models


class Recommend(models.Model):
    performance = models.ForeignKey('performance.Performance', on_delete=models.CASCADE, related_name="recommend")
    recommend_performance = models.ForeignKey('performance.Performance', on_delete=models.CASCADE, related_name="recommend_performance")
    create_date = models.DateTimeField(auto_now_add=True)
    update_date = models.DateTimeField(auto_now=True)

    class Meta:
        managed = False
        db_table = 'recommend'
